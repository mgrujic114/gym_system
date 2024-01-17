package sk2.reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.reservationservice.dto.training.GymCreateDto;
import sk2.reservationservice.dto.training.GymDto;

public interface GymService {
    Page<GymDto> findAll(Pageable pageable);
    GymDto findById(Long id);
    GymDto add(GymCreateDto gymCreateDto);
    GymDto findGymByName(String name);
    void deleteById(Long id);
}
