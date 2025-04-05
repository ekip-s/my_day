package org.myday.mappers;

import org.mapstruct.Mapper;
import org.myday.models.recurring_event.IncomingRecurringEvent;
import org.myday.models.recurring_event.OutgoingRecurringEvent;
import org.myday.models.recurring_event.RecurringEvent;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecurringEventMapper {

    OutgoingRecurringEvent toOutgoing(RecurringEvent event);
    List<OutgoingRecurringEvent> toOutgoingList(List<RecurringEvent> events);
    RecurringEvent toEvent(IncomingRecurringEvent event);
}
