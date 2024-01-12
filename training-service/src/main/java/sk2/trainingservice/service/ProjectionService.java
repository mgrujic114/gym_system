package sk2.trainingservice.service;

import sk2.trainingservice.dto.ProjectionCreateDto;
import sk2.trainingservice.dto.ProjectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectionService {

    Page<ProjectionDto> findAll(Pageable pageable);

    ProjectionDto findById(Long id);

    ProjectionDto add(ProjectionCreateDto projectionCreateDto);

    void deleteById(Long id);
}
