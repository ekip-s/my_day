package org.myday.services.single_event;

import org.myday.models.single_event.IncomingSingleEvent;
import org.myday.models.single_event.OutgoingSingleEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SingleEventsServiceImpl implements SingleEventsService {

    @Override
    public OutgoingSingleEvent getSingleEventById(UUID eventId) {
        return null;
    }

    @Override
    public List<OutgoingSingleEvent> getUserSingleEvent() {
        return List.of();
    }

    @Override
    public OutgoingSingleEvent addSingleEvents(IncomingSingleEvent incomingEvent) {
        return null;
    }

    @Override
    public OutgoingSingleEvent updateSingleEvents(UUID eventId, IncomingSingleEvent incomingEvent) {
        return null;
    }

    @Override
    public void deleteSingleEventById(UUID eventId) {

    }
}
