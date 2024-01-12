package sk2.trainingservice.service.impl;

import sk2.trainingservice.domain.Movie;
import sk2.trainingservice.dto.MovieCreateDto;
import sk2.trainingservice.dto.MovieDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.MovieMapper;
import sk2.trainingservice.repository.MovieRepository;
import sk2.trainingservice.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Page<MovieDto> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movieMapper::movieToMovieDto);
    }

    @Override
    public MovieDto findById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::movieToMovieDto)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id: %d does not exists.", id)));
    }

    @Override
    public MovieDto add(MovieCreateDto movieCreateDto) {
        Movie movie = movieMapper.movieCreateDtoToMovie(movieCreateDto);
        movieRepository.save(movie);
        return movieMapper.movieToMovieDto(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
