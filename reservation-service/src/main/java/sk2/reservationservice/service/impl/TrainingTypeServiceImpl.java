package sk2.reservationservice.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sk2.reservationservice.domain.TrainingType;
import sk2.reservationservice.dto.training.TrainingTypeCreateDto;
import sk2.reservationservice.dto.training.TrainingTypeDto;
import sk2.reservationservice.mapper.TrainingTypeMapper;
import sk2.reservationservice.repository.TrainingTypeRepository;
import sk2.reservationservice.service.TrainingTypeService;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private TrainingTypeRepository trainingTypeRepository;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    @Override
    public Page<TrainingTypeDto> findAll(Pageable pageable) {
        return trainingTypeRepository.findAll(pageable)
                .map(trainingTypeMapper::trainingTypeToTrainingTypeDto);
    }

    @Override
    public TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto) {
        TrainingType trainingType = trainingTypeMapper.trainingTypeCreateDtoToTrainingType(trainingTypeCreateDto);
        trainingTypeRepository.save(trainingType);
        return trainingTypeMapper.trainingTypeToTrainingTypeDto(trainingType);
    }

    @Override
    public void deleteById(Long id) {
        trainingTypeRepository.deleteById(id);
    }
}

