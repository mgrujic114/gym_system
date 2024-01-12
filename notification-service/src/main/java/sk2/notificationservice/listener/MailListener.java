package sk2.notificationservice.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sk2.notificationservice.listener.helper.MessageHelper;
import sk2.notificationservice.service.MailService;

import javax.jms.Message;

@Component
public class MailListener {
    private MessageHelper messageHelper;
    private MailService emailService;

    public MailListener(MessageHelper messageHelper, MailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) {
        emailService.sendSimpleMessage("email", "subject", "poruka");
    }
}
