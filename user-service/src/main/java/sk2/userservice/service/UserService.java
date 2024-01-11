package sk2.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.dto.*;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    DiscountDto findDiscount(Long id);

    UserDto add(UserCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
