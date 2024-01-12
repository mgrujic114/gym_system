package sk2.trainingservice.repository;

import sk2.trainingservice.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
