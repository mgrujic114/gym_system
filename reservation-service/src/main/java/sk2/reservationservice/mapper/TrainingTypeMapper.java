package sk2.reservationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.reservationservice.domain.TrainingType;
import sk2.reservationservice.dto.training.TrainingTypeCreateDto;
import sk2.reservationservice.dto.training.TrainingTypeDto;

@Component
public class TrainingTypeMapper {
    public TrainingTypeDto trainingTypeToTrainingTypeDto(TrainingType trainingType){
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setId(trainingType.getId());
        trainingTypeDto.setType(trainingType.getType());
        trainingTypeDto.setName(trainingType.getName());
        return trainingTypeDto;
    }

    public TrainingType trainingTypeCreateDtoToTrainingType(TrainingTypeCreateDto trainingTypeCreateDto){
        TrainingType trainingType = new TrainingType();
        trainingType.setName(trainingTypeCreateDto.getName());
        trainingType.setType(trainingTypeCreateDto.getType());
        trainingType.setName(trainingTypeCreateDto.getName());
        return trainingType;
    }
}
