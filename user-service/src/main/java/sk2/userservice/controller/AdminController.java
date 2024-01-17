package sk2.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.userservice.dto.UserDto;
import sk2.userservice.secutiry.CheckSecurity;
import sk2.userservice.service.ClientService;
import sk2.userservice.service.UserService;
import sk2.userservice.service.implementation.ClientServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController extends UserController{
    ClientService clientService;
    public AdminController(UserService service, ClientService userService) {
        super(service);
        this.clientService = userService;
    }
    @PostMapping("/restrict-account")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> restrictAccount(@RequestHeader("username") String username) {
        Long id = clientService.verifyUser(username);
        return new ResponseEntity<>(clientService.findClientByID(id), HttpStatus.OK);
    }
}
