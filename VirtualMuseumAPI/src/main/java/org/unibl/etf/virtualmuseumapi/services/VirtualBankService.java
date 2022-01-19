package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualmuseumapi.exceptions.BadRequestException;
import org.unibl.etf.virtualmuseumapi.exceptions.HttpException;
import org.unibl.etf.virtualmuseumapi.model.dto.PurchasePostDTO;
import org.unibl.etf.virtualmuseumapi.model.entities.*;

import java.text.SimpleDateFormat;
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
            mailService.sendTicket(purchasePostDTO.getEmail(), transaction, tourEntity, userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpException("Internal server error.");
        }

        transaction = transactionService.insert(transaction);
        return true;
    }

    private boolean validateCardInfo(PurchasePostDTO purchasePostDTO, CardEntity cardEntity) {
        return purchasePostDTO.getFirstName().toUpperCase().equals(cardEntity.getFirstName().toUpperCase()) &&
               purchasePostDTO.getLastName().toUpperCase().equals(cardEntity.getLastName().toUpperCase()) &&
               purchasePostDTO.getCardType().toUpperCase().equals(cardEntity.getCardType().toUpperCase()) &&
               purchasePostDTO.getExpirationDate().toUpperCase().equals(cardEntity.getExpirationDate().toUpperCase()) &&
               purchasePostDTO.getCvv().toUpperCase().equals(cardEntity.getCvv().toUpperCase());
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
}
