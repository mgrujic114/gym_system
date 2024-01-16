package sk2.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends UserDto{
    private Integer numberOfReservations;
    private Boolean restricted;
}
