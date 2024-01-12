package sk2.reservationservice.client.trainingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GymDto {

    private Long id;
    @JsonProperty("capacity")
    private Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer numberOfSeats) {
        this.capacity = capacity;
    }
}
