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
@RequestMapping("/manager")
public class ManagerController extends UserController {
    private ClientServiceImpl clientService;
    public ManagerController(UserService userService) {
        super(userService);
    }

}
