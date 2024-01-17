package sk2.notificationservice.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.notificationservice.dto.NotificationDto;
import sk2.notificationservice.security.CheckSecurity;
import sk2.notificationservice.service.MailService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private MailService notificationService;

    public NotificationController(MailService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(notificationService.getAllNotificationsForAdmin(pageable), HttpStatus.OK);
    }
}