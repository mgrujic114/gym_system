package sk2.userservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.userservice.dto.*;
import sk2.userservice.secutiry.CheckSecurity;
import sk2.userservice.service.ClientService;
import sk2.userservice.service.UserService;


import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController extends UserController{
    private ClientService clientService;

    public ClientController(UserService service, ClientService clientService) {
        super(service);

        this.clientService = clientService;
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<UserDto> confirmAccount(@RequestHeader("token") String token) {
        Long id = clientService.verifyToken(token);
        return new ResponseEntity<>(clientService.findClientByID(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Register user")
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid ClientCreateDto userCreateDto) {
        return new ResponseEntity<>(clientService.addClient(userCreateDto), HttpStatus.CREATED);
    }

}
