package sk2.notificationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.notificationservice.domain.Notification;
import sk2.notificationservice.dto.ActivationDto;
import sk2.notificationservice.dto.NotificationDto;
import sk2.notificationservice.dto.ReservationNotificationDto;
import sk2.notificationservice.dto.UserDto;

import java.util.List;

@Component
public class NotificationMapper {

    public NotificationDto notificationToNotificationDto (Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setRecipientEmail(notification.getRecipientEmail());
        notificationDto.setType(notification.getType());
        notificationDto.setContent(notification.getContent());
        notificationDto.setSentDate(notification.getSentDate());
        return  notificationDto;
    }

    public Notification activationDtoToNotification (ActivationDto activationDto, String type, String content) {
        Notification notification = new Notification();
        notification.setRecipientEmail(activationDto.getMail());
        notification.setType(type);
        notification.setContent(content);
        return notification;
    }

    public List<Notification> reservationNotifDtoToNotification (ReservationNotificationDto reservationNotificationDto,
                                                                 String typeClient, String contentClient, String typeManager, String contentManager){
        Notification notificationClient = new Notification();
        notificationClient.setRecipientEmail(reservationNotificationDto.getClientMail());
        notificationClient.setType(typeClient);
        notificationClient.setContent(contentClient);

        Notification notificationManager = new Notification();
        notificationManager.setRecipientEmail(reservationNotificationDto.getManagerMail());
        notificationManager.setType(typeManager);
        notificationManager.setContent(contentManager);

        return List.of(notificationClient, notificationManager);
    }

}