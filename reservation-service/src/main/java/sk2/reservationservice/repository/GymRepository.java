package sk2.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk2.reservationservice.domain.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findGymByName(String name);
}
