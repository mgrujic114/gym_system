package sk2.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk2.reservationservice.domain.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findSessionsByTrainingGymId(Long id);
    List<Session> findSessionsByDateBefore(LocalDate date);
}
