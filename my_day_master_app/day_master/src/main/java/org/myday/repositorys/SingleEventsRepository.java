package org.myday.repositorys;

import org.myday.models.single_event.SingleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SingleEventsRepository  extends JpaRepository<SingleEvent, UUID> {

    List<SingleEvent> findByUserIdOrderByCreatedAtDesc(@Param("userId") UUID userId);
}
