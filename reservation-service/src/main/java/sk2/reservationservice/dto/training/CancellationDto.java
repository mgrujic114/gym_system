package sk2.reservationservice.dto.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CancellationDto {
    List<Long> userIds;
    String gymName;
}