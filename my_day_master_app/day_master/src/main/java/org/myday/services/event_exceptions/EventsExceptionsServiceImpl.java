package org.myday.services.event_exceptions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class EventsExceptionsServiceImpl implements EventsExceptionsService {

    @Override
    public void addEventException(UUID eventId, Instant exceptionTime) {

    }

    @Override
    public void deleteEventExceptionById(UUID exceptionId) {

    }
}
