package sk2.reservationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.reservationservice.domain.Training;
import sk2.reservationservice.dto.training.TrainingCreateDto;
import sk2.reservationservice.dto.training.TrainingDto;
import sk2.reservationservice.exception.NotFoundException;
import sk2.reservationservice.repository.GymRepository;
import sk2.reservationservice.repository.TrainingTypeRepository;

@Component
public class TrainingMapper {
    private GymRepository gymRepository;
    private TrainingTypeRepository trainingTypeRepository;
    private GymMapper gymMapper;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingMapper(GymRepository gymRepository, TrainingTypeRepository trainingTypeRepository, GymMapper gymMapper, TrainingTypeMapper trainingTypeMapper) {
        this.gymRepository = gymRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.gymMapper = gymMapper;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public TrainingDto trainingToTrainingDto(Training gymTraining){
        TrainingDto gymTrainingDto = new TrainingDto();
        gymTrainingDto.setId(gymTraining.getId());
        gymTrainingDto.setGymDto(gymMapper.gymToGymDto(gymTraining.getGym()));
        gymTrainingDto.setTrainingTypeDto(trainingTypeMapper.trainingTypeToTrainingTypeDto(gymTraining.getTrainingType()));
        gymTrainingDto.setPrice(gymTraining.getPrice());
        return gymTrainingDto;
    }

    public Training trainingCreateDtoToTraining(TrainingCreateDto gymTrainingCreateDto){
        Training gymTraining = new Training();
        gymTraining.setGym(gymRepository.findById(gymTrainingCreateDto.getGym())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Gym with id: %d does not exists.", gymTrainingCreateDto.getGym()))));
        gymTraining.setTrainingType(trainingTypeRepository.findById(gymTrainingCreateDto.getTrainingType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("TrainingType with id: %d does not exists.", gymTrainingCreateDto.getTrainingType()))));
        gymTraining.setPrice(gymTrainingCreateDto.getPrice());
        return gymTraining;
    }
}
