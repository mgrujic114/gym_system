package sk2.reservationservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk2.reservationservice.domain.Session;
import sk2.reservationservice.domain.Training;
import sk2.reservationservice.domain.Reservation;
import sk2.reservationservice.dto.training.SessionCreateDto;
import sk2.reservationservice.dto.training.SessionDto;
import sk2.reservationservice.dto.CancellationDto;
import sk2.reservationservice.dto.ReservationNotificationDto;
import sk2.reservationservice.listener.helper.MessageHelper;
import sk2.reservationservice.mapper.SessionMapper;
import sk2.reservationservice.repository.SessionRepository;
import sk2.reservationservice.repository.ReservationRepository;
import sk2.reservationservice.service.SessionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private ReservationRepository reservationRepository;
    private SessionMapper sessionMapper;
    private JmsTemplate jmsTemplate;
    private String sessionCancelQueueDestination;
    private MessageHelper messageHelper;

    public SessionServiceImpl(SessionRepository sessionRepository, ReservationRepository reservationRepository, SessionMapper sessionMapper,
                                  JmsTemplate jmsTemplate, @Value("${destination.sessionCancel}") String sessionCancelQueueDestination, MessageHelper messageHelper) {
        this.sessionRepository = sessionRepository;
        this.reservationRepository = reservationRepository;
        this.sessionMapper = sessionMapper;
        this.jmsTemplate = jmsTemplate;
        this.sessionCancelQueueDestination = sessionCancelQueueDestination;
        this.messageHelper = messageHelper;
    }

    @Override
    public List<SessionDto> findAll() {
        LocalDate now = LocalDate.now();
        LocalDate fiveDaysLater = now.plusDays(5);

        List<Session> list = sessionRepository.findSessionsByDateBefore(fiveDaysLater);
        List<SessionDto> res = new ArrayList<>();

        for(Session a: list){
            res.add(sessionMapper.sessionToSessionDto(a));
        }

        return res;
    }
    @Override
    public SessionDto findById(Long id) {
        Session session = sessionRepository.findById(id).get();
        return sessionMapper.sessionToSessionDto(session);
    }

    @Override
    public List<SessionDto> findByGymId(Long id) {
        List<Session> list = sessionRepository.findSessionsByTrainingGymId(id);
        List<SessionDto> res = new ArrayList<>();

        for(Session a: list){
            res.add(sessionMapper.sessionToSessionDto(a));
        }

        return res;
    }

    @Override
    public SessionDto add(SessionCreateDto sessionCreateDto) {
        Session session = sessionMapper.sessionCreateDtoToSession(sessionCreateDto);
        sessionRepository.save(session);
        return sessionMapper.sessionToSessionDto(session);
    }

    @Override
    public void deleteById(Long id) {
        List<Reservation> reservations = reservationRepository.findBySessionId(id);
        List<Long> userIds = new ArrayList<>();

        for(Reservation r: reservations){
            userIds.add(r.getUserId());
            reservationRepository.delete(r);
        }

        CancellationDto cancellationDto  = new CancellationDto(userIds, sessionRepository.findById(id).get().getTraining().getGym().getName());
        jmsTemplate.convertAndSend(sessionCancelQueueDestination, messageHelper.createTextMessage(cancellationDto));

        sessionRepository.deleteById(id);
    }
}
