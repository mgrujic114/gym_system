package sk2.userservice.mapper;


import sk2.userservice.domain.Admin;
import sk2.userservice.domain.Client;
import sk2.userservice.domain.Manager;
import sk2.userservice.domain.User;
import sk2.userservice.dto.*;
import sk2.userservice.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto userToUserDto(User user) {
        if (user instanceof Client) return userToClientDto((Client) user);
        if (user instanceof Admin) return userToAdminDto((Admin) user);
        if (user instanceof Manager) return userToManagerDto((Manager) user);
        else return null;
    }
    public UserDto userToClientDto(Client user) {
        ClientDto userDto = new ClientDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setNumberOfReservations(user.getNumberOfReservations());
        userDto.setRestricted(user.getRestricted());
        return userDto;
    }
    public UserDto userToAdminDto(Admin user) {
        UserDto userDto = new AdminDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
    public UserDto userToManagerDto(Manager user) {
        UserDto userDto = new ManagerDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User userCreateDtoToUser(UserCreateDto user) {
        if (user instanceof ClientCreateDto) return userCreateDtoToClient((ClientCreateDto) user);
        if (user instanceof AdminCreateDto) return userCreateDtoToAdmin((AdminCreateDto) user);
        if (user instanceof ManagerCreateDto) return userCreateDtoToManager((ManagerCreateDto) user);
        else return null;

    }

    public User userCreateDtoToClient(ClientCreateDto userCreateDto) {
        Client user = new Client();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_USER").get());
        user.setNumberOfReservations(0);
        user.setRestricted(false);

        return user;
    }
    public User userCreateDtoToManager(ManagerCreateDto userCreateDto) {
        Manager user = new Manager();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        user.setDateOfEmployment(userCreateDto.getDateOfEmployment());

        return user;
    }
    public User userCreateDtoToAdmin(AdminCreateDto userCreateDto) {
        Admin user = new Admin();
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_ADMIN").get());

        return user;
    }
}
