package sk2.reservationservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sk2.reservationservice.domain.Training;
import sk2.reservationservice.dto.training.TrainingCreateDto;
import sk2.reservationservice.dto.training.TrainingDto;
import sk2.reservationservice.mapper.TrainingMapper;
import sk2.reservationservice.repository.TrainingRepository;
import sk2.reservationservice.service.TrainingService;
@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository gymTrainingRepository;
    private TrainingMapper gymTrainingMapper;

    public TrainingServiceImpl(TrainingRepository gymTrainingRepository, TrainingMapper gymTrainingMapper) {
        this.gymTrainingRepository = gymTrainingRepository;
        this.gymTrainingMapper = gymTrainingMapper;
    }

    @Override
    public Page<TrainingDto> findAll(Pageable pageable) {
        return gymTrainingRepository.findAll(pageable)
                .map(gymTrainingMapper::trainingToTrainingDto);
    }

    @Override
    public TrainingDto add(TrainingCreateDto gymTrainingCreateDto) {
        Training gymTraining = gymTrainingMapper.trainingCreateDtoToTraining(gymTrainingCreateDto);
        gymTrainingRepository.save(gymTraining);
        return gymTrainingMapper.trainingToTrainingDto(gymTraining);
    }

    @Override
    public void deleteById(Long id) {
        gymTrainingRepository.deleteById(id);
    }
}
