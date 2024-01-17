package sk2.reservationservice.dto.training;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymCreateDto {
    private String name;
    private String description;
    private Integer capacity;
}
