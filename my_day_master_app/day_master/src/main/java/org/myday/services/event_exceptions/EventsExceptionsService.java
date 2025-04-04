package org.myday.services.event_exceptions;

import java.time.Instant;
import java.util.UUID;

public interface EventsExceptionsService {

    void addEventException(UUID eventId, Instant exceptionTime);
    void deleteEventExceptionById(UUID exceptionId);
}
