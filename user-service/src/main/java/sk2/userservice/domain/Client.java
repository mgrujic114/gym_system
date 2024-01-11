package sk2.userservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
@Getter
@Setter
@Entity
public class Client extends User{
    private Integer numberOfReservations;
}
