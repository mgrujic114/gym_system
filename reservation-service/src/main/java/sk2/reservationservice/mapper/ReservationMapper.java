package sk2.reservationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.reservationservice.domain.Reservation;
import sk2.reservationservice.dto.ReservationDto;

@Component
public class ReservationMapper {
    private SessionMapper appointmentMapper;

    public ReservationMapper(SessionMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    public ReservationDto reservationToReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setSession(appointmentMapper.sessionToSessionDto(reservation.getSession()));
        return reservationDto;
    }
}
