package sk2.trainingservice.controller;

import sk2.trainingservice.dto.ProjectionCreateDto;
import sk2.trainingservice.dto.ProjectionDto;
import sk2.trainingservice.secutiry.CheckSecurity;
import sk2.trainingservice.service.ProjectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/projection")
public class ProjectionController {

    private ProjectionService projectionService;

    public ProjectionController(ProjectionService projectionService) {
        this.projectionService = projectionService;
    }

    @ApiOperation(value = "Get all projections")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<ProjectionDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(projectionService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<ProjectionDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(projectionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ProjectionDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ProjectionCreateDto projectionCreateDto) {
        return new ResponseEntity<>(projectionService.add(projectionCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        projectionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
