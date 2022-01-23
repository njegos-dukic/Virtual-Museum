package org.unibl.etf.virtualmuseumapi.utils;

import org.unibl.etf.virtualmuseumapi.services.MailService;

public class RunnableEmail implements Runnable {

    private final MailService mailService;
    private final String recipient;
    private final String tourInfo;
    private final String specifics;

    public RunnableEmail(MailService mailService, String recipient, String tourInfo, String specifics) {
        this.mailService = mailService;
        this.recipient = recipient;
        this.tourInfo = tourInfo;
        this.specifics = specifics;
    }

    @Override
    public void run() {
        System.out.println("Sending mail: " + specifics + "\n\n" + tourInfo);
        mailService.sendSimpleMail(recipient, specifics + "\n\n" + tourInfo);
    }
}


