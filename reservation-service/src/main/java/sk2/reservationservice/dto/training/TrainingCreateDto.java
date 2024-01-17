package sk2.reservationservice.dto.training;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TrainingCreateDto {
    private Long gym;
    private Long trainingType;
    private BigDecimal price;
}
