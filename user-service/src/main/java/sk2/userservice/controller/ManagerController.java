package sk2.userservice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk2.userservice.service.UserService;
@RestController
@RequestMapping("/manager")
public class ManagerController extends UserController {
    public ManagerController(UserService userService) {
        super(userService);
    }
}
