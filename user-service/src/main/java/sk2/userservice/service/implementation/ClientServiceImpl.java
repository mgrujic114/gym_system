package sk2.userservice.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.domain.Client;
import sk2.userservice.domain.User;
import sk2.userservice.dto.ClientCreateDto;
import sk2.userservice.dto.ClientDto;
import sk2.userservice.dto.UserDto;
import sk2.userservice.mapper.UserMapper;
import sk2.userservice.repository.UserRepository;
import sk2.userservice.repository.UserStatusRepository;
import sk2.userservice.secutiry.service.TokenService;

public class ClientServiceImpl extends UserServiceImpl {
    public ClientServiceImpl(UserRepository userRepository, TokenService tokenService, UserStatusRepository userStatusRepository, UserMapper userMapper) {
        super(userRepository, tokenService, userStatusRepository, userMapper);
    }

    public UserDto findClientByID(Long id){
        Client user = (Client) getUserRepository().findById(id).get();
        return getUserMapper().userToUserDto(user);
    }

    public Long verifyUser(String token) {
        Client client = (Client) getUserRepository().findByUsername(token);

        client.setRestricted(!client.getRestricted());

        getUserRepository().save(client);
        return client.getId();
    }

}
