package sk2.trainingservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Training movie;
    @ManyToOne
    private Gym screen;
    private BigDecimal price;

    public Session() {

    }

    public Session(Training movie, Gym screen, BigDecimal price) {
        this.movie = movie;
        this.screen = screen;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getMovie() {
        return movie;
    }

    public void setMovie(Training movie) {
        this.movie = movie;
    }

    public Gym getScreen() {
        return screen;
    }

    public void setScreen(Gym screen) {
        this.screen = screen;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
