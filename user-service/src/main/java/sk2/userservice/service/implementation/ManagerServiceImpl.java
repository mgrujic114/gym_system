package sk2.userservice.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk2.userservice.domain.Manager;
import sk2.userservice.dto.ManagerCreateDto;
import sk2.userservice.dto.ManagerDto;
import sk2.userservice.dto.UserDto;
import sk2.userservice.mapper.UserMapper;
import sk2.userservice.repository.RoleRepository;
import sk2.userservice.repository.UserRepository;
import sk2.userservice.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private UserRepository managerRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    public ManagerServiceImpl(UserRepository managerRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.managerRepository = managerRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDto> findAllManagers(Pageable pageable) {
        return managerRepository.findUserByRole(roleRepository.findRoleByName("ROLE_MANAGER").get(), pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public ManagerDto findById(Long id) {
        Manager manager = (Manager) managerRepository.findById(id).get();
        return (ManagerDto) userMapper.userToUserDto(manager);
    }

    @Override
    public ManagerDto addManager(ManagerCreateDto managerCreateDto) {
        Manager manager = (Manager) userMapper.userCreateDtoToUser(managerCreateDto);
        managerRepository.save(manager);
        return (ManagerDto) userMapper.userToUserDto(manager);
    }
}
