package sk2.userservice.service.implementation;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk2.userservice.domain.Client;

import sk2.userservice.domain.Role;
import sk2.userservice.domain.User;
import sk2.userservice.domain.UserStatus;
import sk2.userservice.dto.*;
import sk2.userservice.exception.NotFoundException;
import sk2.userservice.mapper.UserMapper;
import sk2.userservice.repository.RoleRepository;

import sk2.userservice.repository.UserRepository;
import sk2.userservice.repository.UserStatusRepository;
import sk2.userservice.secutiry.service.TokenService;
import sk2.userservice.service.ClientService;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private UserRepository clientRepository;
    private UserStatusRepository userStatusRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private TokenService tokenService;


    public ClientServiceImpl(UserRepository clientRepository, UserStatusRepository userStatusRepository, RoleRepository roleRepository,
                             TokenService tokenService, UserMapper userMapper) {
        this.clientRepository = clientRepository;
        this.userStatusRepository = userStatusRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.userMapper = userMapper;

    }

    @Override
    public Page<UserDto> findAllClients(Pageable pageable) {
        return clientRepository.findUserByRole(roleRepository.findRoleByName("ROLE_CLIENT").get(), pageable)
                .map(userMapper::userToUserDto);
    }

    public UserDto findClientByID(Long id){
        Client user = (Client) clientRepository.findById(id).get();
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto findClientByRole(Role role) {
        return null;
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        User user = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found", id)));
        Client client = (Client) user;
        List<UserStatus> userStatusList = userStatusRepository.findAll();

        Integer discount = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaxNumberOfReservations() >= client.getNumberOfReservations()
                        && userStatus.getMinNumberOfReservations() <= client.getNumberOfReservations())
                .findAny()
                .get()
                .getDiscount();
        return new DiscountDto(discount);
    }

    @Override
    public ClientDto addClient(ClientCreateDto clientCreateDto) {
        if(clientRepository.existsByEmail(clientCreateDto.getEmail())){
            throw new RuntimeException("User already exists!");
        }
        Client client = (Client) userMapper.userCreateDtoToUser(clientCreateDto);
        clientRepository.save(client);

        return (ClientDto) userMapper.userToClientDto(client);
    }

    @Override
    public Long verifyToken(String token) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        User user = clientRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public Long verifyUser(String token) {
        Client client = (Client) clientRepository.findByUsername(token);

        client.setRestricted(!client.getRestricted());

        clientRepository.save(client);
        return client.getId();
    }

}
