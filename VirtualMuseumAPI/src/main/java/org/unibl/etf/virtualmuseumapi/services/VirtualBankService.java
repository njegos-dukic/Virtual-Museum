package org.unibl.etf.virtualmuseumapi.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.exceptions.BadRequestException;
import org.unibl.etf.virtualmuseumapi.exceptions.HttpException;
import org.unibl.etf.virtualmuseumapi.model.dto.PurchasePostDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.*;
import org.unibl.etf.virtualmuseumapi.utils.RunnableEmail;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class VirtualBankService {

    private final CardService cardService;
    private final TourService tourService;
    private final UserService userService;
    private final CardUserService cardUserService;
    private final TransactionService transactionService;
    private final MailService mailService;
    private final ThreadPoolTaskScheduler taskScheduler;

    private static final int TICKET_NUMBER_LENGTH = 128;

    public Boolean purchaseTicket(PurchasePostDTO purchasePostDTO) {

        // Request valid;
        if (purchasePostDTO.getCardNumber() == null || purchasePostDTO.getUserId() == null || purchasePostDTO.getTourId() == null)
            throw new BadRequestException("Invalid request.");

        // Card exists;
        CardEntity cardEntity = cardService.getByCardNumberOrElseNull(purchasePostDTO.getCardNumber());
        if (cardEntity == null)
            throw new BadRequestException("Invalid card number.");

        // Tour exists;
        TourEntity tourEntity = tourService.getByIdOrNull(purchasePostDTO.getTourId());
        if (tourEntity == null)
            throw new BadRequestException("Invalid tour id.");

        // Tour starts in less than one hour;
        if (!Instant.now().isBefore(tourEntity.getStart().minus(1, ChronoUnit.HOURS)))
                throw new BadRequestException("Tour starts in less than one hour or is already finished.");

        // User exists;
        UserEntity userEntity = userService.getUserByIdOrNull(purchasePostDTO.getUserId());
        if (userEntity == null)
            throw new BadRequestException("Invalid user id.");

        // Card user exists or created;
        CardUserEntityId cardUserId = new CardUserEntityId(userEntity.getId(), cardEntity.getId());
        CardUserEntity cardUser = cardUserService.getByIdOrElseNull(cardUserId);
        if (cardUser == null) {
            cardUser = new CardUserEntity(cardUserId);
            cardUser = cardUserService.insertCardUser(cardUser);
        }

        TransactionEntity transaction = new TransactionEntity();
        transaction.setUserId(cardUser.getId().getUserId());
        transaction.setCardNumber(cardUser.getId().getCardNumber());
        transaction.setTourId(tourEntity.getId());
        transaction.setAmount(tourEntity.getPrice());

        if (!validateCardInfo(purchasePostDTO, cardEntity)) {
            handleTransaction(transaction, false, "Invalid card info");
        }

        if (!cardEntity.getIsEnabled()) {
            handleTransaction(transaction, false, "Card is disabled by the owner");
        }

        if (!checkFunds(cardEntity, tourEntity)) {
            handleTransaction(transaction, false, "Insufficient funds");
        }

        Double newCardBalance = cardEntity.getBalance() - tourEntity.getPrice();
        cardEntity.setBalance(newCardBalance);
        cardEntity = cardService.updateCardEntity(cardEntity);

        handleTransaction(transaction, true, "Successfully bought");

        try {
            String ticketAsString = toTicketString(transaction, tourEntity, userEntity);
            String recipient = purchasePostDTO.getEmail();
            createPDF(transaction.getTicketNumber(), ticketAsString);
            mailService.sendTicket(recipient, transaction.getTicketNumber());
            scheduleNotification(tourEntity.getStart().minus(1, ChronoUnit.HOURS), recipient, ticketAsString, "Starts in 1 hour.");
            scheduleNotification(tourEntity.getStart().plus((long) (tourEntity.getDuration() * 60 - 5), ChronoUnit.MINUTES), recipient, ticketAsString, "Ends in 5 minutes.");

            // mailService.sendTicket(userEntity.getEmail(), transaction.getTicketNumber());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpException("Internal server error.");
        }

        transaction = transactionService.insert(transaction);
        return true;
    }

    private boolean validateCardInfo(PurchasePostDTO purchasePostDTO, CardEntity cardEntity) {
        return purchasePostDTO.getFirstName().equalsIgnoreCase(cardEntity.getFirstName()) &&
               purchasePostDTO.getLastName().equalsIgnoreCase(cardEntity.getLastName()) &&
               purchasePostDTO.getCardType().equalsIgnoreCase(cardEntity.getCardType()) &&
               purchasePostDTO.getExpirationDate().equalsIgnoreCase(cardEntity.getExpirationDate()) &&
               purchasePostDTO.getCvv().equalsIgnoreCase(cardEntity.getCvv());
    }

    private boolean checkFunds(CardEntity cardEntity, TourEntity tourEntity) {
        return cardEntity.getBalance() >= tourEntity.getPrice();
    }

    private String generateRandomAlphaNumericString() {
        int leftLimit = '0';
        int rightLimit = 'z';
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                     .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // Exclude unicode chars;
                     .limit(TICKET_NUMBER_LENGTH)
                     .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                     .toString();
    }

    private void handleTransaction(TransactionEntity transaction, Boolean success, String message) {
        String transactionDateTimeAsString = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm.").format(new Date());
        transaction.setIsSuccess(success);
        transaction.setTransactionInfo(message + ": " + transactionDateTimeAsString);
        if (success)
            transaction.setTicketNumber(generateRandomAlphaNumericString());
        transactionService.insert(transaction);
        if (!success)
            throw new BadRequestException(message + ".");
    }

    private void checkForTicketsFolder() {
        File directory = new File("tickets");
        if (!directory.exists())
             directory.mkdir();
    }

    private void createPDF(String ticketNumber, String pdfStringContent) throws FileNotFoundException {
        checkForTicketsFolder();
        PdfDocument pdf = new PdfDocument(new PdfWriter("tickets" + File.separator + ticketNumber + ".pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph(pdfStringContent));
        document.close();
    }

    public String toTicketString(TransactionEntity transaction, TourEntity tour, UserEntity user) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("First and last name: ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("\r\n");
        stringBuilder.append("Name of the tour: ").append(tour.getName()).append("\r\n");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm 'H'");

        stringBuilder.append("Bought: ").append(formatter.format(new Date())).append("\r\n");

        Date myDate = Date.from(tour.getStart());
        String formattedDate = formatter.format(myDate);

        stringBuilder.append("Start date and time: ").append(formattedDate).append("\r\n");
        stringBuilder.append("Tour duration: ").append(tour.getDuration()).append(" H").append("\r\n");
        stringBuilder.append("Ticket price: ").append(tour.getPrice()).append(" EUR").append("\r\n\r\n");

        stringBuilder.append("Info for ticket number: \r\n").append(transaction.getTicketNumber()).append("\r\n").append("\r\n");

        return stringBuilder.toString();
    }

    public void scheduleNotification(Instant instant, String recipient, String tourInfo, String specifics) {
        taskScheduler.schedule(
                new RunnableEmail(mailService, recipient, tourInfo, specifics),
                Date.from(instant));
    }
}
