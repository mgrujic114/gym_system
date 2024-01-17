package sk2.reservationservice.dto.training;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class SessionDto {
    private Long id;
    private LocalDate date;
    private String startTime;
    private String endTime;
    @JsonProperty("gymTraining")
    private TrainingDto gymTrainingDto;
    private Integer capacity;
}
