package sk2.trainingservice.repository;

import sk2.trainingservice.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
