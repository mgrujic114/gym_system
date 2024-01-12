package sk2.trainingservice.runner;


import sk2.trainingservice.domain.Training;
import sk2.trainingservice.domain.Session;
import sk2.trainingservice.domain.Gym;
import sk2.trainingservice.repository.TrainingRepository;
import sk2.trainingservice.repository.SessionRepository;
import sk2.trainingservice.repository.GymRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private TrainingRepository movieRepository;
    private GymRepository screenRepository;
    private SessionRepository projectionRepository;

    public TestDataRunner(TrainingRepository movieRepository, GymRepository screenRepository, SessionRepository projectionRepository) {
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.projectionRepository = projectionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //insert movies
        Training movie1 = movieRepository.save(new Training("movie 1", "description movie 1"));
        Training movie2 = movieRepository.save(new Training("movie 2", "description movie 2"));
        //insert screens
        Gym screen1 = screenRepository.save(new Gym(20));
        Gym screen2 = screenRepository.save(new Gym(30));
        //insert projections
        projectionRepository.save(new Session(movie1, screen1, BigDecimal.TEN));
        projectionRepository.save(new Session(movie1, screen2, BigDecimal.TEN));
        projectionRepository.save(new Session(movie2, screen2, BigDecimal.TEN));
    }
}
