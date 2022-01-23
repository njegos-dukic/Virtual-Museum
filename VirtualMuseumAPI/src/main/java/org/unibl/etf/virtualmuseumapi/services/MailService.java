package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@AllArgsConstructor
public class MailService {

    private JavaMailSender emailSender;

    private final static String EMAIL_FROM = "njegos.dukic.998@gmail.com";
    private final static String EMAIL_SUBJECT = "IP Virtual Museum";

    public void sendTicket(String recipient, String ticketNumber) throws MessagingException {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_FROM);
            helper.setTo(recipient);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText("Virtual Museum: Ticket attached.");

            FileSystemResource file = new FileSystemResource(new File("tickets" + File.separator + ticketNumber + ".pdf"));
            helper.addAttachment("VM Ticket.pdf", file);

            emailSender.send(message);
    }

    public void sendSimpleMail(String recipient, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL_FROM);
        message.setTo(recipient);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(content);
        emailSender.send(message);
    }
}
