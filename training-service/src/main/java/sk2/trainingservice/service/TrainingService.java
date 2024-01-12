package sk2.trainingservice.service;

import sk2.trainingservice.dto.TrainingCreateDto;
import sk2.trainingservice.dto.TrainingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingService {

    Page<TrainingDto> findAll(Pageable pageable);

    TrainingDto findById(Long id);

    TrainingDto add(TrainingCreateDto movieCreateDto);

    void deleteById(Long id);
}
