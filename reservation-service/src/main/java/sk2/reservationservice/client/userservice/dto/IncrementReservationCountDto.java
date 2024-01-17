package sk2.reservationservice.client.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncrementReservationCountDto {
    private Long userId;
    private String gymName;


    public IncrementReservationCountDto(Long userId, String name) {
    }


}
