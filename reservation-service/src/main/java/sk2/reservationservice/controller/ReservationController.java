package sk2.reservationservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sk2.reservationservice.dto.ReservationCreateDto;
import sk2.reservationservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /*@GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                     Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }*/

    @PostMapping("/book")
    public ResponseEntity<Void> addReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        reservationService.addReservation(reservationCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
