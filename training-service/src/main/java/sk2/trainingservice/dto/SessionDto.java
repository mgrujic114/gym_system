package sk2.trainingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SessionDto {

    private Long id;
    @JsonProperty("training")
    private TrainingDto movieDto;
    @JsonProperty("gym")
    private GymDto screenDto;
    private BigDecimal price;
}
