package sk2.reservationservice.dto;

import lombok.Getter;
import lombok.Setter;
import sk2.reservationservice.client.trainingservice.dto.SessionDto;

import java.math.BigDecimal;

@Getter
@Setter
public class ReservationDto {
    private Long id;
    private SessionDto session;
    private Long userId;
    private BigDecimal price;


}
