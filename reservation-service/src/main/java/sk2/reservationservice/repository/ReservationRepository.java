package sk2.reservationservice.repository;

import sk2.reservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByUserIdAndSessionId(Long userId, Long sessionId);
}
