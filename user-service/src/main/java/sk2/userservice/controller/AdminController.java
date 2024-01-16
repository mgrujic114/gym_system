package sk2.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk2.userservice.dto.UserDto;
import sk2.userservice.service.UserService;
import sk2.userservice.service.implementation.ClientServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController extends UserController{
    ClientServiceImpl clientService;
    public AdminController(UserService userService) {
        super(userService);
    }
    @GetMapping("/restrict-account")
    public ResponseEntity<UserDto> restrictAccount(@RequestHeader("username") String token) {
        Long id = clientService.verifyUser(token);
        return new ResponseEntity<>(clientService.findClientByID(id), HttpStatus.OK);
    }
}
