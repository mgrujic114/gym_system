package sk2.reservationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.reservationservice.domain.Session;
import sk2.reservationservice.domain.Training;
import sk2.reservationservice.dto.training.SessionCreateDto;
import sk2.reservationservice.dto.training.SessionDto;
import sk2.reservationservice.exception.NotFoundException;
import sk2.reservationservice.repository.TrainingRepository;

@Component
public class SessionMapper {
    
    private TrainingRepository gymTrainingRepository;
    private TrainingMapper gymTrainingMapper;

    public SessionMapper(TrainingRepository gymTrainingRepository, TrainingMapper gymTrainingMapper) {
        this.gymTrainingRepository = gymTrainingRepository;
        this.gymTrainingMapper = gymTrainingMapper;
    }

    public SessionDto sessionToSessionDto(Session session){
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setDate(session.getDate());
        sessionDto.setStartTime(session.getStartTime());
        sessionDto.setEndTime(session.getEndTime());
        sessionDto.setGymTrainingDto(gymTrainingMapper.trainingToTrainingDto(session.getTraining()));
        sessionDto.setCapacity(gymTrainingMapper.trainingToTrainingDto(session.getTraining()).getGymDto().getCapacity());
        return sessionDto;
    }

    public Session sessionCreateDtoToSession(SessionCreateDto sessionCreateDto){
        Session session = new Session();
        session.setDate(sessionCreateDto.getDate());
        session.setStartTime(sessionCreateDto.getStartTime());
        session.setEndTime(sessionCreateDto.getEndTime());
        Training gymTraining = gymTrainingRepository.findById(sessionCreateDto.getGymTraining())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Gym with id: %d does not exists.", sessionCreateDto.getGymTraining())));
        session.setTraining(gymTraining);
        session.setCapacity(gymTraining.getGym().getCapacity());
        return session;
    }
}
