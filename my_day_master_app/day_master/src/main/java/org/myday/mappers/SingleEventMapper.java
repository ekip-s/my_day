package org.myday.mappers;

import org.mapstruct.Mapper;
import org.myday.models.single_event.IncomingSingleEvent;
import org.myday.models.single_event.OutgoingSingleEvent;
import org.myday.models.single_event.SingleEvent;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SingleEventMapper {

    OutgoingSingleEvent toOutgoing(SingleEvent event);
    List<OutgoingSingleEvent> toOutgoingList(List<SingleEvent> events);
    SingleEvent toEvent(IncomingSingleEvent incomingSingleEvent);
}
