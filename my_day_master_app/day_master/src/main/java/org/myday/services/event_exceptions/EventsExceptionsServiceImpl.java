package org.myday.services.event_exceptions;


import org.myday.exceptions.ConflictException;
import org.myday.exceptions.NotFoundException;
import org.myday.models.events_exception.EventException;
import org.myday.models.recurring_event.RecurringEvent;
import org.myday.repositorys.EventsExceptionsRepository;
import org.myday.services.recurring_event.RecurringEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class EventsExceptionsServiceImpl implements EventsExceptionsService {

    private final EventsExceptionsRepository eventsExceptionsRepository;
    private final RecurringEventsService recurringEventsService;

    @Autowired
    public EventsExceptionsServiceImpl(EventsExceptionsRepository eventsExceptionsRepository, RecurringEventsService recurringEventsService) {
        this.eventsExceptionsRepository = eventsExceptionsRepository;
        this.recurringEventsService = recurringEventsService;
    }

    @Override
    @Transactional
    public void addEventException(UUID eventId, Instant exceptionTime) {
        RecurringEvent event = recurringEventsService.getEventById(eventId);
        EventException eventException = new EventException(null, event, exceptionTime);
        eventsExceptionsRepository.save(eventException);
    }

    @Override
    @Transactional
    public void deleteEventExceptionById(UUID exceptionId) {
        EventException exception = getExceptionById(exceptionId);

        if (!getUserId().equals(exception.getRecurringEvent().getUserId())) {
            throw new ConflictException("Ты не можешь управлять чужими исключениями", "Конфликт");
        }
        eventsExceptionsRepository.deleteById(exceptionId);
    }

    private EventException getExceptionById(UUID exceptionId) {
        return eventsExceptionsRepository
                .findById(exceptionId)
                .orElseThrow(() -> new NotFoundException("Нет исключения с переданным id", "Нет данных"));
    }

    private UUID getUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }
}
