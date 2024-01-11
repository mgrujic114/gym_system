package sk2.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class ManagerCreateDto extends UserCreateDto{
    @NotBlank
    private LocalDate dateOfEmployment;
}
