package sk2.reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.reservationservice.dto.training.TrainingCreateDto;
import sk2.reservationservice.dto.training.TrainingDto;

public interface TrainingService {
    Page<TrainingDto> findAll(Pageable pageable);
    TrainingDto add(TrainingCreateDto gymTrainingCreateDto);
    void deleteById(Long id);
}
