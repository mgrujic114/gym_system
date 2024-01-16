package sk2.userservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Client extends User{
    private Integer numberOfReservations;
    private Boolean restricted;

    public Integer getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(Integer numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
}
