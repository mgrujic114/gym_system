package sk2.notificationservice.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sk2.notificationservice.dto.UserDto;
import sk2.notificationservice.listener.helper.MessageHelper;
import sk2.notificationservice.service.MailService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private MessageHelper messageHelper;
    private MailService emailService;

    public NotificationListener(MessageHelper messageHelper, MailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }
   /* @JmsListener(destination = "${destination.sendConfirmationEmail}", concurrency = "5-10")
    public void confirmationMail(Message message) throws JMSException, InterruptedException {
        ActivationDto activationDto = messageHelper.getMessage(message, ActivationDto.class);
        notificationService.sendActivationEmail(activationDto);
    }*/

    @JmsListener(destination = "${destination.sendPasswordEmail}", concurrency = "5-10")
    public void passwordResetMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
        emailService.sendPasswordResetEmail(user);
    }

    @JmsListener(destination = "${destination.sendReservationEmail}", concurrency = "5-10")
    public void successfulReservationMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendSuccessfulReservationNotification(user);
    }

    @JmsListener(destination = "${destination.sendCancellationEmail}", concurrency = "5-10")
    public void cancellationMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendCancellationNotification(user);
    }

    @JmsListener(destination = "${destination.sendRemainderEmail}", concurrency = "5-10")
    public void remainderMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendCancellationNotification(user);
    }
}
