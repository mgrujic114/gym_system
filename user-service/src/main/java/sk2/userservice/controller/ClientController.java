package sk2.userservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk2.userservice.dto.ClientCreateDto;
import sk2.userservice.dto.ClientDto;
import sk2.userservice.dto.DiscountDto;
import sk2.userservice.dto.UserDto;
import sk2.userservice.secutiry.CheckSecurity;
import sk2.userservice.service.ClientService;
import sk2.userservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Get all users")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllClients(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(clientService.findAllClients(pageable), HttpStatus.OK);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<UserDto> confirmAccount(@RequestHeader("token") String token) {
        Long id = clientService.verifyToken(token);
        return new ResponseEntity<>(clientService.findClientByID(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findDiscount(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Register client")
    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.addClient(clientCreateDto), HttpStatus.CREATED);
    }
}
