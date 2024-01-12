package sk2.trainingservice.service.impl;

import sk2.trainingservice.domain.Screen;
import sk2.trainingservice.dto.ScreenCreateDto;
import sk2.trainingservice.dto.ScreenDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.ScreenMapper;
import sk2.trainingservice.repository.ScreenRepository;
import sk2.trainingservice.service.ScreenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScreenServiceImpl implements ScreenService {

    private ScreenRepository screenRepository;
    private ScreenMapper screenMapper;

    public ScreenServiceImpl(ScreenRepository screenRepository, ScreenMapper screenMapper) {
        this.screenRepository = screenRepository;
        this.screenMapper = screenMapper;
    }

    @Override
    public Page<ScreenDto> findAll(Pageable pageable) {
        return screenRepository.findAll(pageable)
                .map(screenMapper::screenToScreenDto);
    }

    @Override
    public ScreenDto findById(Long id) {
        return screenRepository.findById(id)
                .map(screenMapper::screenToScreenDto)
                .orElseThrow(() -> new NotFoundException(String.format("Screen with id: %d does not exists.", id)));
    }

    @Override
    public ScreenDto add(ScreenCreateDto screenCreateDto) {
        Screen screen = screenMapper.screenCreateDtoToScreen(screenCreateDto);
        screenRepository.save(screen);
        return screenMapper.screenToScreenDto(screen);
    }

    @Override
    public void deleteById(Long id) {
        screenRepository.deleteById(id);
    }

}
