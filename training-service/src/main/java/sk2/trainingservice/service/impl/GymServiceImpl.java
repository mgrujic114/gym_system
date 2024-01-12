package sk2.trainingservice.service.impl;

import sk2.trainingservice.domain.Gym;
import sk2.trainingservice.dto.GymCreateDto;
import sk2.trainingservice.dto.GymDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.GymMapper;
import sk2.trainingservice.repository.GymRepository;
import sk2.trainingservice.service.GymService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GymServiceImpl implements GymService {

    private GymRepository screenRepository;
    private GymMapper screenMapper;

    public GymServiceImpl(GymRepository screenRepository, GymMapper screenMapper) {
        this.screenRepository = screenRepository;
        this.screenMapper = screenMapper;
    }

    @Override
    public Page<GymDto> findAll(Pageable pageable) {
        return screenRepository.findAll(pageable)
                .map(screenMapper::screenToScreenDto);
    }

    @Override
    public GymDto findById(Long id) {
        return screenRepository.findById(id)
                .map(screenMapper::screenToScreenDto)
                .orElseThrow(() -> new NotFoundException(String.format("Screen with id: %d does not exists.", id)));
    }

    @Override
    public GymDto add(GymCreateDto screenCreateDto) {
        Gym screen = screenMapper.screenCreateDtoToScreen(screenCreateDto);
        screenRepository.save(screen);
        return screenMapper.screenToScreenDto(screen);
    }

    @Override
    public void deleteById(Long id) {
        screenRepository.deleteById(id);
    }

}
