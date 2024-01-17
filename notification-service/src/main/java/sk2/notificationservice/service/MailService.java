package sk2.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.notificationservice.domain.Notification;
import sk2.notificationservice.dto.*;

import java.util.List;
import java.util.Map;

public interface MailService{
   /* void sendActivationEmail(ActivationDto activationDto) throws InterruptedException;*/
    void sendPasswordResetEmail(UserDto userDto) throws InterruptedException;
    void sendSuccessfulReservationNotification(ReservationNotificationDto reservationNotificationDto) throws InterruptedException;
    /*void sendAppointmentCancellationNotification(NotificationCancellationDto notificationCancellationDto);
    void sendReservationCancellationNotification(NotificationCancellationDto notificationCancellationDto);*/
    void sendReminderNotification(ReservationNotificationDto reservationNotificationDto);

    Page<NotificationDto> getAllNotificationsForAdmin(Pageable pageable);
}