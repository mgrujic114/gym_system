package sk2.trainingservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.trainingservice.dto.SessionCreateDto;
import sk2.trainingservice.dto.SessionDto;

public interface SessionService {

    Page<SessionDto> findAll(Pageable pageable);

    SessionDto findById(Long id);

    SessionDto add(SessionCreateDto projectionCreateDto);

    void deleteById(Long id);
}
