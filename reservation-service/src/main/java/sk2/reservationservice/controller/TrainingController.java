package sk2.reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.reservationservice.dto.training.TrainingCreateDto;
import sk2.reservationservice.dto.training.TrainingDto;
import sk2.reservationservice.security.CheckSecurity;
import sk2.reservationservice.service.TrainingService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/gym-training")
public class TrainingController {
    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @ApiOperation(value = "Get all gym trainings")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<TrainingDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(trainingService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<TrainingDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid TrainingCreateDto gymTrainingCreateDto){
        return new ResponseEntity<>(trainingService.add(gymTrainingCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        trainingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
