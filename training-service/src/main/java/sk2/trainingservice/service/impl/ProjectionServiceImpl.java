package sk2.trainingservice.service.impl;


import sk2.trainingservice.domain.Projection;
import sk2.trainingservice.dto.ProjectionCreateDto;
import sk2.trainingservice.dto.ProjectionDto;
import sk2.trainingservice.exception.NotFoundException;
import sk2.trainingservice.mapper.ProjectionMapper;
import sk2.trainingservice.repository.ProjectionRepository;
import sk2.trainingservice.service.ProjectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectionServiceImpl implements ProjectionService {

    private ProjectionRepository projectionRepository;
    private ProjectionMapper projectionMapper;

    public ProjectionServiceImpl(ProjectionRepository projectionRepository, ProjectionMapper projectionMapper) {
        this.projectionRepository = projectionRepository;
        this.projectionMapper = projectionMapper;
    }

    @Override
    public Page<ProjectionDto> findAll(Pageable pageable) {
        return projectionRepository.findAll(pageable)
                .map(projectionMapper::projectionToProjectionDto);
    }

    @Override
    public ProjectionDto findById(Long id) {
        return projectionRepository.findById(id)
                .map(projectionMapper::projectionToProjectionDto)
                .orElseThrow(() -> new NotFoundException(String.format("Projection with id: %d does not exists.", id)));
    }

    @Override
    public ProjectionDto add(ProjectionCreateDto projectionCreateDto) {
        Projection projection = projectionMapper.projectionCreateDtoToProjection(projectionCreateDto);
        projectionRepository.save(projection);
        return projectionMapper.projectionToProjectionDto(projection);
    }

    @Override
    public void deleteById(Long id) {
        projectionRepository.deleteById(id);
    }
}

