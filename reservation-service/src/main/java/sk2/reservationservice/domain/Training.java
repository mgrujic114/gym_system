package sk2.reservationservice.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Gym gym;
    @ManyToOne
    private TrainingType trainingType;
    private BigDecimal price;

    public Training(Gym gym, TrainingType trainingType, BigDecimal price) {
        this.gym = gym;
        this.trainingType = trainingType;
        this.price = price;
    }
}
