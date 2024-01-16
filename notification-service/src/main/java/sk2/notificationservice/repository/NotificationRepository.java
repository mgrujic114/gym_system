package sk2.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk2.notificationservice.domain.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientEmail(String recipientEmail);
}