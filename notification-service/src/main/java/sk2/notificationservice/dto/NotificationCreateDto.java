package sk2.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCreateDto {
    private String type;
    private String recipientEmail;
}