package sk2.trainingservice.service;


import sk2.trainingservice.dto.GymCreateDto;
import sk2.trainingservice.dto.GymDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymService {

    Page<GymDto> findAll(Pageable pageable);

    GymDto findById(Long id);

    GymDto add(GymCreateDto screenCreateDto);

    void deleteById(Long id);
}
