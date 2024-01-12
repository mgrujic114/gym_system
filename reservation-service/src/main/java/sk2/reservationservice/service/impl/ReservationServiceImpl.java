package sk2.reservationservice.service.impl;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import sk2.reservationservice.client.trainingservice.dto.SessionDto;
import sk2.reservationservice.client.userservice.dto.DiscountDto;
import sk2.reservationservice.domain.Reservation;
import sk2.reservationservice.dto.ReservationCreateDto;
import sk2.reservationservice.exception.NotFoundException;
import sk2.reservationservice.listener.helper.MessageHelper;
import sk2.reservationservice.repository.ReservationRepository;
import sk2.reservationservice.service.ReservationService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private RestTemplate trainingServiceRestTemplate;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
    private String incrementReservationCountDestination;
    private MessageHelper messageHelper;
    private Retry movieServiceRetry;
    private Bulkhead movieServiceBulkhead;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RestTemplate trainingServiceRestTemplate,
                                  RestTemplate userServiceRestTemplate,
                                  JmsTemplate jmsTemplate,
                                  @Value("${destination.incrementReservationCount}") String incrementReservationCountDestination,
                                  MessageHelper messageHelper,
                                  Retry movieServiceRetry,
                                  Bulkhead movieServiceBulkhead) {
        this.reservationRepository = reservationRepository;
        this.trainingServiceRestTemplate = trainingServiceRestTemplate;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.incrementReservationCountDestination = incrementReservationCountDestination;
        this.messageHelper = messageHelper;
        this.movieServiceRetry = movieServiceRetry;
        this.movieServiceBulkhead = movieServiceBulkhead;
    }

    @Override
    public void addReservation(ReservationCreateDto reservationCreateDto) {
        SessionDto projectionDto = Bulkhead.decorateSupplier(movieServiceBulkhead, ()-> Retry.decorateSupplier(movieServiceRetry, ()->getProjection(reservationCreateDto.getSessionId())).get()).get();

        //get discount from user service
        ResponseEntity<DiscountDto> discountDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                reservationCreateDto.getUserId() + "/discount", HttpMethod.GET, null, DiscountDto.class);
        //calculate price
        BigDecimal price = projectionDto.getPrice().divide(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(100 - discountDtoResponseEntity.getBody().getDiscount()));
        //save reservation
        Reservation reservation = new Reservation(reservationCreateDto.getUserId(), reservationCreateDto.getSessionId(), price);
        reservationRepository.save(reservation);

    }

    private SessionDto getProjection(Long projectionId) {
        //get projection from movie service
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting projection for id: " + projectionId);
        try {
            Thread.sleep(5000);
            return trainingServiceRestTemplate.exchange("/session/"
                    + projectionId, HttpMethod.GET, null, SessionDto.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Projection with id: %d not found.", projectionId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Internal server error.");
        }
    }
}
