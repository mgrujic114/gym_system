package sk2.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk2.userservice.service.UserService;
@RestController
@RequestMapping("/admin")
public class AdminController extends UserController{
    public AdminController(UserService userService) {
        super(userService);
    }
}
