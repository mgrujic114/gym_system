package sk2.trainingservice.repository;

import sk2.trainingservice.domain.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
