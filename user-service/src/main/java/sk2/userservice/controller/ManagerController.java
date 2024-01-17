package sk2.userservice.controller;



import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk2.userservice.domain.Client;
import sk2.userservice.dto.ClientCreateDto;
import sk2.userservice.dto.ManagerCreateDto;
import sk2.userservice.dto.UserDto;
import sk2.userservice.service.ClientService;
import sk2.userservice.service.ManagerService;
import sk2.userservice.service.UserService;
import sk2.userservice.service.implementation.ClientServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController extends UserController{
    private ManagerService clientService;
    @ApiOperation(value = "Register manager")
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid ManagerCreateDto userCreateDto) {
        return new ResponseEntity<>(clientService.addManager(userCreateDto), HttpStatus.CREATED);
    }


    public ManagerController(UserService userService, ManagerService clientService) {
        super(userService);
        this.clientService = clientService;
    }
}
