package sk2.trainingservice.mapper;

import sk2.trainingservice.domain.Screen;
import sk2.trainingservice.dto.ScreenCreateDto;
import sk2.trainingservice.dto.ScreenDto;
import org.springframework.stereotype.Component;

@Component
public class ScreenMapper {

    public ScreenDto screenToScreenDto(Screen screen) {
        ScreenDto screenDto = new ScreenDto();
        screenDto.setId(screen.getId());
        screenDto.setNumberOfSeats(screen.getNumberOfSeats());
        return screenDto;
    }

    public Screen screenCreateDtoToScreen(ScreenCreateDto screenCreateDto) {
        Screen screen = new Screen();
        screen.setNumberOfSeats(screenCreateDto.getNumberOfSeats());
        return screen;
    }
}

