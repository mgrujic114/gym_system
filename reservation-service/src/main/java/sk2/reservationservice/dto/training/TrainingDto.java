package sk2.reservationservice.dto.training;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TrainingDto {
    private Long id;
    @JsonProperty("gym")
    private GymDto gymDto;
    @JsonProperty("trainingType")
    private TrainingTypeDto trainingTypeDto;
    private BigDecimal price;
}
