package sk2.trainingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GymDto {

    private Long id;
    @JsonProperty("number_of_seats")
    private Integer numberOfSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
