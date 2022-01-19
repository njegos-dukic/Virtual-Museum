package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.unibl.etf.virtualmuseumapi.model.entities.TourEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.TransactionEntity;
import org.unibl.etf.virtualmuseumapi.model.entities.UserEntity;

@Component
@AllArgsConstructor
public class MailService {

    private JavaMailSender emailSender;

    @Value("${mail.username}")
    private final static String EMAIL_FROM = "";
    private final static String EMAIL_SUBJECT = "Virtual Museum Ticket";

    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_FROM);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendTicket(String recipient, TransactionEntity transaction, TourEntity tour, UserEntity user) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("njegos.dukic.998@gmail.com");
        message.setTo(recipient);
        message.setSubject(EMAIL_SUBJECT);
        String content = "Virtual Museum Ticket" + "\n\n" +
                         "\tUser: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                         "\tTour: " + tour.getName() + " at " + tour.getStart() + "\n" +
                         "\tPrice: " + tour.getPrice() + " EUR" + "\n" +
                         "\tTicket number: " + transaction.getTicketNumber() + "\n\n" +
                         "\tTour link: http://localhost:9000/tours/current/" + tour.getId();
        message.setText(content);
        emailSender.send(message);
    }

}
