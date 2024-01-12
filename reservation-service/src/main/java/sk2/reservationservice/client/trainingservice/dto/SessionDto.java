package sk2.reservationservice.client.trainingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class SessionDto {

    private Long id;
    @JsonProperty("training")
    private TrainingDto trainingDto;
    @JsonProperty("gym")
    private GymDto gymDto;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
