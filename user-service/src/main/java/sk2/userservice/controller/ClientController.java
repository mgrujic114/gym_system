package sk2.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk2.userservice.service.UserService;
@RestController
@RequestMapping("/user")
public class ClientController extends UserController{
    public ClientController(UserService userService) {
        super(userService);
    }
}
