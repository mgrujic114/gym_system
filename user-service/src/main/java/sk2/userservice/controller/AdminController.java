package sk2.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.userservice.dto.UserDto;
import sk2.userservice.secutiry.CheckSecurity;
import sk2.userservice.service.ClientService;
import sk2.userservice.service.UserService;
import sk2.userservice.service.implementation.ClientServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController extends UserController{
    ClientService clientService;
    public AdminController(UserService service, ClientService userService) {
        super(service);
        this.clientService = userService;
    }
    @PutMapping("/restrict-account")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> restrictAccount(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.restrict(id), HttpStatus.OK);
    }
}
