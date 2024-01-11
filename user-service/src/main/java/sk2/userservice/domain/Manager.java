package sk2.userservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Manager extends User{
    private LocalDate dateOfEmployment;
    //private Room room;
}
