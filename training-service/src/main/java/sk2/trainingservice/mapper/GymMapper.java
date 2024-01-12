package sk2.trainingservice.mapper;

import sk2.trainingservice.domain.Gym;
import sk2.trainingservice.dto.GymCreateDto;
import sk2.trainingservice.dto.GymDto;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {

    public GymDto screenToScreenDto(Gym screen) {
        GymDto screenDto = new GymDto();
        screenDto.setId(screen.getId());
        screenDto.setNumberOfSeats(screen.getNumberOfSeats());
        return screenDto;
    }

    public Gym screenCreateDtoToScreen(GymCreateDto screenCreateDto) {
        Gym screen = new Gym();
        screen.setNumberOfSeats(screenCreateDto.getNumberOfSeats());
        return screen;
    }
}

