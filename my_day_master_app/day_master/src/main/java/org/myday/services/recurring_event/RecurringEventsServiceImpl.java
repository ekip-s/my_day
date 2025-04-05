package org.myday.services.recurring_event;

import org.myday.exceptions.ConflictException;
import org.myday.exceptions.NotFoundException;
import org.myday.mappers.RecurringEventMapper;
import org.myday.models.recurring_event.IncomingRecurringEvent;
import org.myday.models.recurring_event.OutgoingRecurringEvent;
import org.myday.models.recurring_event.RecurringEvent;
import org.myday.repositorys.RecurringEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RecurringEventsServiceImpl implements RecurringEventsService {

    private final RecurringEventsRepository recurringEventsRepository;
    private final RecurringEventMapper recurringEventMapper;

    @Autowired
    public RecurringEventsServiceImpl(RecurringEventsRepository recurringEventsRepository, RecurringEventMapper recurringEventMapper) {
        this.recurringEventsRepository = recurringEventsRepository;
        this.recurringEventMapper = recurringEventMapper;
    }

    @Override
    public OutgoingRecurringEvent getRecurringEventById(UUID eventId) {
        RecurringEvent recurringEvent = recurringEventsRepository
                .findById(eventId)
                .orElseThrow(() -> new NotFoundException("Нет события, с переданным id", "Нет данных"));

        if (ownerValidation(recurringEvent)) {
            return recurringEventMapper.toOutgoing(recurringEvent);
        } else {
            throw new ConflictException("Запись не пренедлежит пользователю", "Конфликт");
        }
    }

    @Override
    public List<OutgoingRecurringEvent> getUserRecurringEvents() {
        return recurringEventMapper
                .toOutgoingList(recurringEventsRepository
                        .findByUserIdOrderByCreatedAtDesc(getUserId()));
    }

    @Override
    @Transactional
    public OutgoingRecurringEvent addRecurringEvent(IncomingRecurringEvent incomingRecurringEvent) {
        RecurringEvent recurringEvent = recurringEventMapper.toEvent(incomingRecurringEvent);
        recurringEvent.setUserId(getUserId());
        return recurringEventMapper.toOutgoing(recurringEventsRepository.save(recurringEvent));
    }

    @Override
    @Transactional
    public OutgoingRecurringEvent updateRecurringEvent(UUID eventId, IncomingRecurringEvent incomingRecurringEvent) {
        ownerValidation(eventId);

        RecurringEvent recurringEvent = recurringEventMapper.toEvent(incomingRecurringEvent);
        recurringEvent.setId(eventId);
        recurringEvent.setUserId(getUserId());

        return recurringEventMapper.toOutgoing(recurringEventsRepository.save(recurringEvent));
    }

    @Override
    @Transactional
    public OutgoingRecurringEvent pauseRecurringEvent(UUID eventId, Boolean isDeleted) {
        RecurringEvent event = getEventById(eventId);

        if (!event.getIsDeleted().equals(isDeleted)) {
            event.setIsDeleted(isDeleted);
        }

        return recurringEventMapper.toOutgoing(recurringEventsRepository.save(event));
    }

    @Override
    @Transactional
    public void deleteRecurringEventById(UUID eventId) {
        ownerValidation(eventId);
        recurringEventsRepository.deleteById(eventId);
    }

    @Override
    public RecurringEvent getEventById(UUID eventId) {
        RecurringEvent recurringEvent = recurringEventsRepository
                .findById(eventId)
                .orElseThrow(() -> new NotFoundException("Нет события, с переданным id", "Нет данных"));

        if (ownerValidation(recurringEvent)) {
            return recurringEvent;
        } else {
            throw new ConflictException("Запись не пренедлежит пользователю", "Конфликт");
        }
    }

    private boolean ownerValidation(RecurringEvent event) {
        return event.getUserId().equals(getUserId());
    }

    private void ownerValidation(UUID eventId) {
        RecurringEvent recurringEvent = recurringEventsRepository
                .findById(eventId)
                .orElseThrow(() -> new NotFoundException("Нет события, с переданным id", "Нет данных"));

        if (!ownerValidation(recurringEvent)) {
            throw new ConflictException("Запись не пренедлежит пользователю", "Конфликт");
        }
    }

    private UUID getUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }
}
