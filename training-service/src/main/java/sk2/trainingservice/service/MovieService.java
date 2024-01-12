package sk2.trainingservice.service;

import sk2.trainingservice.dto.MovieCreateDto;
import sk2.trainingservice.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieDto> findAll(Pageable pageable);

    MovieDto findById(Long id);

    MovieDto add(MovieCreateDto movieCreateDto);

    void deleteById(Long id);
}
