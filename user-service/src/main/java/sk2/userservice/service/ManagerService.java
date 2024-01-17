package sk2.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.dto.ManagerCreateDto;
import sk2.userservice.dto.ManagerDto;
import sk2.userservice.dto.UserDto;

public interface ManagerService {
    Page<UserDto> findAllManagers(Pageable pageable);
    ManagerDto findById(Long id);
    ManagerDto addManager(ManagerCreateDto managerCreateDto);
}
