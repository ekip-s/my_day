package org.myday.services.recurring_event;

import org.myday.models.recurring_event.IncomingRecurringEvent;
import org.myday.models.recurring_event.OutgoingRecurringEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RecurringEventsServiceImpl implements RecurringEventsService {

    @Override
    public OutgoingRecurringEvent getRecurringEventById(UUID eventId) {
        return null;
    }

    @Override
    public List<OutgoingRecurringEvent> getUserRecurringEvents() {
        return List.of();
    }

    @Override
    public OutgoingRecurringEvent addRecurringEvent(IncomingRecurringEvent incomingRecurringEvent) {
        return null;
    }

    @Override
    public OutgoingRecurringEvent updateRecurringEvent(UUID eventId, IncomingRecurringEvent incomingRecurringEvent) {
        return null;
    }

    @Override
    public OutgoingRecurringEvent pauseRecurringEvent(UUID eventId, Boolean isDeleted) {
        return null;
    }

    @Override
    public void deleteRecurringEventById(UUID eventId) {

    }
}
