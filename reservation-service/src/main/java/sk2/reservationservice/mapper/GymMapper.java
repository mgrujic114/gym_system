package sk2.reservationservice.mapper;

import org.springframework.stereotype.Component;
import sk2.reservationservice.domain.Gym;
import sk2.reservationservice.dto.training.GymCreateDto;
import sk2.reservationservice.dto.training.GymDto;

@Component
public class GymMapper {
    public GymDto gymToGymDto(Gym gym){
        GymDto gymDto = new GymDto();
        gymDto.setId(gym.getId());
        gymDto.setName(gym.getName());
        gymDto.setDescription(gym.getDescription());
        gymDto.setCapacity(gym.getCapacity());
        return gymDto;
    }

    public Gym gymCreateDtoToGym(GymCreateDto gymCreateDto){
        Gym gym = new Gym();
        gym.setName(gymCreateDto.getName());
        gym.setDescription(gymCreateDto.getDescription());
        gym.setCapacity(gymCreateDto.getCapacity());
        return gym;
    }
}
