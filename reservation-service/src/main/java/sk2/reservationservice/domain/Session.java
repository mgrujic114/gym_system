package sk2.reservationservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate date;
    private String startTime;
    private String endTime;
    @ManyToOne
    private Training training;
    private Integer capacity;

    public Session(LocalDate date, String startTime, String endTime, Training gymTraining, Integer capacity) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.training = training;
        this.capacity = capacity;
    }
}
