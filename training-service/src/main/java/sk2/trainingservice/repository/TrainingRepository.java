package sk2.trainingservice.repository;

import sk2.trainingservice.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
