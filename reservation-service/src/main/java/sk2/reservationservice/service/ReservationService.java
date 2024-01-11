package sk2.reservationservice.service;

import sk2.reservationservice.dto.ReservationCreateDto;

public interface ReservationService {

    void addReservation(ReservationCreateDto reservationCreateDto);
}
