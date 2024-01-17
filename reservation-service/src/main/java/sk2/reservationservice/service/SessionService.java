package sk2.reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.reservationservice.dto.training.SessionCreateDto;
import sk2.reservationservice.dto.training.SessionDto;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<SessionDto> findAll();
    SessionDto findById(Long id);
    List<SessionDto> findByGymId(Long id);
    SessionDto add(SessionCreateDto sessionCreateDto);
    void deleteById(Long id);
}
