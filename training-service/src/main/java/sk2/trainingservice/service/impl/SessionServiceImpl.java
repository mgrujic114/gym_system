package sk2.trainingservice.service.impl;


import sk2.trainingservice.domain.Session;
import sk2.trainingservice.dto.SessionCreateDto;
import sk2.trainingservice.dto.SessionDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.SessionMapper;
import sk2.trainingservice.repository.SessionRepository;
import sk2.trainingservice.service.SessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository projectionRepository;
    private SessionMapper projectionMapper;

    public SessionServiceImpl(SessionRepository projectionRepository, SessionMapper projectionMapper) {
        this.projectionRepository = projectionRepository;
        this.projectionMapper = projectionMapper;
    }

    @Override
    public Page<SessionDto> findAll(Pageable pageable) {
        return projectionRepository.findAll(pageable)
                .map(projectionMapper::projectionToProjectionDto);
    }

    @Override
    public SessionDto findById(Long id) {
        return projectionRepository.findById(id)
                .map(projectionMapper::projectionToProjectionDto)
                .orElseThrow(() -> new NotFoundException(String.format("Projection with id: %d does not exists.", id)));
    }

    @Override
    public SessionDto add(SessionCreateDto projectionCreateDto) {
        Session projection = projectionMapper.projectionCreateDtoToProjection(projectionCreateDto);
        projectionRepository.save(projection);
        return projectionMapper.projectionToProjectionDto(projection);
    }

    @Override
    public void deleteById(Long id) {
        projectionRepository.deleteById(id);
    }
}

