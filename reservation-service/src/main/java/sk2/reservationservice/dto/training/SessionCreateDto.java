package sk2.reservationservice.dto.training;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SessionCreateDto {
    private LocalDate date;
    private String startTime;
    private String endTime;
    private Long gymTraining;
}
