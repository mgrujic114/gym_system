package sk2.trainingservice.mapper;

import sk2.trainingservice.domain.Session;
import sk2.trainingservice.dto.SessionCreateDto;
import sk2.trainingservice.dto.SessionDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.repository.TrainingRepository;
import sk2.trainingservice.repository.GymRepository;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    private TrainingRepository movieRepository;
    private GymRepository screenRepository;
    private TrainingMapper movieMapper;
    private GymMapper screenMapper;

    public SessionMapper(TrainingRepository movieRepository, GymRepository screenRepository, TrainingMapper movieMapper, GymMapper screenMapper) {
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.movieMapper = movieMapper;
        this.screenMapper = screenMapper;
    }

    public SessionDto projectionToProjectionDto(Session projection) {
        SessionDto projectionDto = new SessionDto();
        projectionDto.setId(projection.getId());
        projectionDto.setMovieDto(movieMapper.movieToMovieDto(projection.getMovie()));
        projectionDto.setScreenDto(screenMapper.screenToScreenDto(projection.getScreen()));
        projectionDto.setPrice(projection.getPrice());
        return projectionDto;
    }

    public Session projectionCreateDtoToProjection(SessionCreateDto projectionCreateDto) {
        Session projection = new Session();
        projection.setMovie(movieRepository.findById(projectionCreateDto.getMovieId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Movie with id: %d does not exists.", projectionCreateDto.getMovieId()))));
        projection.setScreen(screenRepository.findById(projectionCreateDto.getScreenId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Screen with id: %d does not exists.", projectionCreateDto.getScreenId()))));
        projection.setPrice(projectionCreateDto.getPrice());
        return projection;
    }
}
