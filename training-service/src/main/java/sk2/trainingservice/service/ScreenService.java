package sk2.trainingservice.service;


import sk2.trainingservice.dto.ScreenCreateDto;
import sk2.trainingservice.dto.ScreenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScreenService {

    Page<ScreenDto> findAll(Pageable pageable);

    ScreenDto findById(Long id);

    ScreenDto add(ScreenCreateDto screenCreateDto);

    void deleteById(Long id);
}
