package sk2.trainingservice.service.impl;

import sk2.trainingservice.domain.Training;
import sk2.trainingservice.dto.TrainingCreateDto;
import sk2.trainingservice.dto.TrainingDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.TrainingMapper;
import sk2.trainingservice.repository.TrainingRepository;
import sk2.trainingservice.service.TrainingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository movieRepository;
    private TrainingMapper movieMapper;

    public TrainingServiceImpl(TrainingRepository movieRepository, TrainingMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Page<TrainingDto> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movieMapper::movieToMovieDto);
    }

    @Override
    public TrainingDto findById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::movieToMovieDto)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id: %d does not exists.", id)));
    }

    @Override
    public TrainingDto add(TrainingCreateDto movieCreateDto) {
        Training movie = movieMapper.movieCreateDtoToMovie(movieCreateDto);
        movieRepository.save(movie);
        return movieMapper.movieToMovieDto(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
