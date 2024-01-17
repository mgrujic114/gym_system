package sk2.reservationservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.reservationservice.dto.training.TrainingTypeCreateDto;
import sk2.reservationservice.dto.training.TrainingTypeDto;

public interface TrainingTypeService {
    Page<TrainingTypeDto> findAll(Pageable pageable);
    TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto);
    void deleteById(Long id);
}
