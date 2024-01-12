package sk2.trainingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class SessionDto {

    private Long id;
    @JsonProperty("training")
    private TrainingDto movieDto;
    @JsonProperty("gym")
    private GymDto screenDto;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingDto getMovieDto() {
        return movieDto;
    }

    public void setMovieDto(TrainingDto movieDto) {
        this.movieDto = movieDto;
    }

    public GymDto getScreenDto() {
        return screenDto;
    }

    public void setScreenDto(GymDto screenDto) {
        this.screenDto = screenDto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
