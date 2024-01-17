package sk2.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk2.reservationservice.domain.TrainingType;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
}
