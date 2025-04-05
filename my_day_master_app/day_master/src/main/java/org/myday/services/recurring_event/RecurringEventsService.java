package org.myday.services.recurring_event;

import org.myday.models.recurring_event.IncomingRecurringEvent;
import org.myday.models.recurring_event.OutgoingRecurringEvent;
import org.myday.models.recurring_event.RecurringEvent;

import java.util.List;
import java.util.UUID;

public interface RecurringEventsService {

    OutgoingRecurringEvent getRecurringEventById(UUID eventId);
    List<OutgoingRecurringEvent> getUserRecurringEvents();
    OutgoingRecurringEvent addRecurringEvent(IncomingRecurringEvent incomingRecurringEvent);
    OutgoingRecurringEvent updateRecurringEvent(UUID eventId, IncomingRecurringEvent incomingRecurringEvent);
    OutgoingRecurringEvent pauseRecurringEvent(UUID eventId, Boolean isDeleted);
    void deleteRecurringEventById(UUID eventId);
    RecurringEvent getEventById(UUID eventId);
}
