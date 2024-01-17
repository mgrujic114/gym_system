package sk2.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk2.reservationservice.domain.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
