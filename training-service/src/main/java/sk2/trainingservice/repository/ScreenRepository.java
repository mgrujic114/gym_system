package sk2.trainingservice.repository;

import sk2.trainingservice.domain.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
