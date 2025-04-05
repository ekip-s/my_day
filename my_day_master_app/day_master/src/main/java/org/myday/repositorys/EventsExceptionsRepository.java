package org.myday.repositorys;

import org.myday.models.events_exception.EventException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventsExceptionsRepository extends JpaRepository<EventException, UUID> {
}
