package sk2.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sk2.userservice.dto.ClientCreateDto;
import sk2.userservice.dto.ClientDto;
import sk2.userservice.dto.DiscountDto;
import sk2.userservice.dto.UserDto;

public interface ClientService {
    Page<UserDto> findAllClients(Pageable pageable);
    UserDto findClientByID(Long id);
    DiscountDto findDiscount(Long id);
    ClientDto addClient(ClientCreateDto clientCreateDto);
    /*void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto);*/
    Long verifyToken(String token);

}
