package sk2.reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.reservationservice.dto.ReservationCreateDto;
import sk2.reservationservice.dto.ReservationDto;

public interface ReservationService {

    Page<ReservationDto> findAll(Pageable pageable);
    ReservationDto addReservation(ReservationCreateDto reservationCreateDto);
    void deleteById(Long id);
}
