package sk2.reservationservice.dto.training;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymDto {
    private Long id;
    private String name;
    private String description;
    private Integer capacity;
}
