package sk2.reservationservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sk2.reservationservice.domain.Gym;
import sk2.reservationservice.dto.training.GymCreateDto;
import sk2.reservationservice.dto.training.GymDto;
import sk2.reservationservice.mapper.GymMapper;
import sk2.reservationservice.repository.GymRepository;
import sk2.reservationservice.service.GymService;

@Service
public class GymServiceImpl implements GymService {
    private GymRepository gymRepository;
    private GymMapper gymMapper;

    public GymServiceImpl(GymRepository gymRepository, GymMapper gymMapper) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    @Override
    public Page<GymDto> findAll(Pageable pageable) {
        return gymRepository.findAll(pageable)
                .map(gymMapper::gymToGymDto);
    }

    @Override
    public GymDto findById(Long id) {
        Gym gym = gymRepository.findById(id).get();
        return gymMapper.gymToGymDto(gym);
    }

    @Override
    public GymDto add(GymCreateDto gymCreateDto) {
        Gym gym = gymMapper.gymCreateDtoToGym(gymCreateDto);
        gymRepository.save(gym);
        return gymMapper.gymToGymDto(gym);
    }

    @Override
    public GymDto findGymByName(String name) {
        Gym gym = gymRepository.findGymByName(name);
        return gymMapper.gymToGymDto(gym);
    }

    @Override
    public void deleteById(Long id) {
        gymRepository.deleteById(id);
    }
}
