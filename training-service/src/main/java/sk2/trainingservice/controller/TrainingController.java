package sk2.trainingservice.controller;

import sk2.trainingservice.dto.MovieCreateDto;
import sk2.trainingservice.dto.MovieDto;
import sk2.trainingservice.secutiry.CheckSecurity;
import sk2.trainingservice.service.MovieService;
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
@RequestMapping("/movie")
public class TrainingController {

    private MovieService movieService;

    public TrainingController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ApiOperation(value = "Get all movies")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<MovieDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(movieService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<MovieDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<MovieDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid MovieCreateDto movieCreateDto) {
        return new ResponseEntity<>(movieService.add(movieCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
