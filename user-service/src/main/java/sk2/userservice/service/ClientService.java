package sk2.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.domain.Role;
import sk2.userservice.dto.*;

public interface ClientService {
    Page<UserDto> findAllClients(Pageable pageable);
    UserDto findClientByID(Long id);
    UserDto findClientByRole(Role role);
    DiscountDto findDiscount(Long id);
    ClientDto addClient(ClientCreateDto clientCreateDto);
    /*void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto);*/
    Long verifyToken(String token);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);


    Long verifyUser(String username);

    UserDto restrict(Long id);
}
