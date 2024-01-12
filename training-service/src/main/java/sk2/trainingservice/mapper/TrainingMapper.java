package sk2.trainingservice.mapper;

import sk2.trainingservice.domain.Training;
import sk2.trainingservice.dto.TrainingCreateDto;
import sk2.trainingservice.dto.TrainingDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto movieToMovieDto(Training movie) {
        TrainingDto movieDto = new TrainingDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }

    public Training movieCreateDtoToMovie(TrainingCreateDto movieCreateDto) {
        Training movie = new Training();
        movie.setTitle(movieCreateDto.getTitle());
        movie.setDescription(movieCreateDto.getDescription());
        return movie;
    }
}
