package sk2.reservationservice.service.impl;


import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sk2.reservationservice.client.userservice.dto.DiscountDto;
import sk2.reservationservice.client.userservice.dto.IncrementReservationCountDto;
import sk2.reservationservice.domain.Session;
import sk2.reservationservice.domain.Reservation;
import sk2.reservationservice.dto.CancellationDto;
import sk2.reservationservice.dto.ReservationCreateDto;
import sk2.reservationservice.dto.ReservationDto;
import sk2.reservationservice.exception.NotFoundException;
import sk2.reservationservice.listener.helper.MessageHelper;
import sk2.reservationservice.mapper.ReservationMapper;
import sk2.reservationservice.repository.SessionRepository;
import sk2.reservationservice.repository.ReservationRepository;
import sk2.reservationservice.service.ReservationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private SessionRepository appointmentRepository;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String incrementReservationCountDestination;
    private String reservationCancellationQueue;
    private MessageHelper messageHelper;
    private Retry reservationServiceRetry;
    private Bulkhead reservationServiceBulkhead;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                  SessionRepository appointmentRepository, RestTemplate userServiceRestTemplate, JmsTemplate jmsTemplate,
                                  @Value("${destination.incrementReservationCount}") String incrementReservationCountDestination, @Value("${destination.reservationCancel}") String reservationCancellationQueue, MessageHelper messageHelper,
                                  Retry reservationServiceRetry, Bulkhead reservationServiceBulkhead) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.appointmentRepository = appointmentRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.incrementReservationCountDestination = incrementReservationCountDestination;
        this.reservationCancellationQueue = reservationCancellationQueue;
        this.messageHelper = messageHelper;
        this.reservationServiceRetry = reservationServiceRetry;
        this.reservationServiceBulkhead = reservationServiceBulkhead;
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable)
                .map(reservationMapper::reservationToReservationDto);
    }

    @Override
    public ReservationDto addReservation(ReservationCreateDto reservationCreateDto) {
        Session appointment = appointmentRepository.findById(reservationCreateDto.getSessionId()).get();
        if (appointment.getCapacity() == 0) {
            throw new RuntimeException("Appointment is full!");
        }
        appointment.setCapacity(appointment.getCapacity() - 1);
        appointmentRepository.save(appointment);

        DiscountDto discountDto = Bulkhead.decorateSupplier(reservationServiceBulkhead,
                () -> Retry.decorateSupplier(reservationServiceRetry, () -> getDiscount(reservationCreateDto.getUserId())).get()).get();

        BigDecimal price = appointment.getTraining().getPrice().divide(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(100 - Objects.requireNonNull(discountDto.getDiscount())));

        Reservation reservation = new Reservation(reservationCreateDto.getUserId(), appointment, price);
        reservationRepository.save(reservation);
        jmsTemplate.convertAndSend(incrementReservationCountDestination, messageHelper.createTextMessage(new IncrementReservationCountDto(reservationCreateDto.getUserId(), appointment.getTraining().getGym().getName())));
        return reservationMapper.reservationToReservationDto(reservation);
    }

    private DiscountDto getDiscount(Long userId) {
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting discount for id: " + userId);
        try {
            Thread.sleep(5000);
            return userServiceRestTemplate.exchange("/client/" + userId + "/discount", HttpMethod.GET, null, DiscountDto.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Discount for User with id: %d not found.", userId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
            throw new RuntimeException("Internal server error.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.getSession().setCapacity(reservation.getSession().getCapacity() + 1);
        CancellationDto cancellationDto = new CancellationDto(List.of(reservation.getUserId()), reservation.getSession().getTraining().getGym().getName());
        jmsTemplate.convertAndSend(reservationCancellationQueue, messageHelper.createTextMessage(cancellationDto));
        reservationRepository.deleteById(id);
    }
}