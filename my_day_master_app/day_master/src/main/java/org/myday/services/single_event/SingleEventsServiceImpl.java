package org.myday.services.single_event;

import org.myday.exceptions.ConflictException;
import org.myday.exceptions.NotFoundException;
import org.myday.mappers.SingleEventMapper;
import org.myday.models.single_event.IncomingSingleEvent;
import org.myday.models.single_event.OutgoingSingleEvent;
import org.myday.models.single_event.SingleEvent;
import org.myday.repositorys.SingleEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SingleEventsServiceImpl implements SingleEventsService {

    private final SingleEventsRepository singleEventsRepository;
    private final SingleEventMapper singleEventMapper;

    @Autowired
    public SingleEventsServiceImpl(SingleEventsRepository singleEventsRepository, SingleEventMapper singleEventMapper) {
        this.singleEventsRepository = singleEventsRepository;
        this.singleEventMapper = singleEventMapper;
    }

    @Override
    public OutgoingSingleEvent getSingleEventById(UUID eventId) {
        SingleEvent singleEvent = singleEventsRepository
                .findById(eventId)
                .orElseThrow(() -> new NotFoundException("Нет события, с переданным id", "Нет данных"));

        if (ownerValidation(singleEvent)) {
            return singleEventMapper.toOutgoing(singleEvent);
        } else {
            throw new ConflictException("Запись не пренедлежит пользователю", "Конфликт");
        }
    }

    @Override
    public List<OutgoingSingleEvent> getUserSingleEvent() {
        return singleEventMapper
                .toOutgoingList(singleEventsRepository
                        .findByUserIdOrderByCreatedAtDesc(getUserId()));
    }

    @Override
    @Transactional
    public OutgoingSingleEvent addSingleEvents(IncomingSingleEvent incomingEvent) {
        SingleEvent singleEvent = singleEventMapper.toEvent(incomingEvent);
        singleEvent.setUserId(getUserId());
        return singleEventMapper.toOutgoing(singleEventsRepository.save(singleEvent));
    }

    @Override
    @Transactional
    public OutgoingSingleEvent updateSingleEvents(UUID eventId, IncomingSingleEvent incomingEvent) {
        ownerValidation(eventId);

        SingleEvent singleEvent = singleEventMapper.toEvent(incomingEvent);
        singleEvent.setId(eventId);
        singleEvent.setUserId(getUserId());
        return singleEventMapper.toOutgoing(singleEventsRepository.save(singleEvent));
    }

    @Override
    @Transactional
    public void deleteSingleEventById(UUID eventId) {
        ownerValidation(eventId);
        singleEventsRepository.deleteById(eventId);
    }

    private boolean ownerValidation(SingleEvent singleEvent) {
        return singleEvent.getUserId().equals(getUserId());
    }

    private UUID getUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }

    private void ownerValidation(UUID eventId) {
        SingleEvent singleEvent = singleEventsRepository
                .findById(eventId)
                .orElseThrow(() -> new NotFoundException("Нет события, с переданным id", "Нет данных"));

        if (!ownerValidation(singleEvent)) {
            throw new ConflictException("Запись не пренедлежит пользователю", "Конфликт");
        }
    }
}
