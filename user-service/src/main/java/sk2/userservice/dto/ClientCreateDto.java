package sk2.userservice.dto;

import javax.validation.constraints.NotNull;

public class ClientCreateDto extends UserCreateDto{

    private Integer numberOfReservations;
    @NotNull
    private Boolean restricted;
}
