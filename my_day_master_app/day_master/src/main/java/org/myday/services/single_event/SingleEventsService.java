package org.myday.services.single_event;

import org.myday.models.single_event.IncomingSingleEvent;
import org.myday.models.single_event.OutgoingSingleEvent;

import java.util.List;
import java.util.UUID;

public interface SingleEventsService {

    OutgoingSingleEvent getSingleEventById(UUID eventId);
    List<OutgoingSingleEvent> getUserSingleEvent();
    OutgoingSingleEvent addSingleEvents(IncomingSingleEvent incomingEvent);
    OutgoingSingleEvent updateSingleEvents(UUID eventId, IncomingSingleEvent incomingEvent);
    void deleteSingleEventById(UUID eventId);
}
