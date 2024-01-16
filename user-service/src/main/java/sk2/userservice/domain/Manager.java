package sk2.userservice.domain;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Manager extends User{
    @CreatedDate
    private LocalDate dateOfEmployment;
    private String gym;

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }
}
