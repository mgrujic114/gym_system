package sk2.reservationservice.dto;


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
