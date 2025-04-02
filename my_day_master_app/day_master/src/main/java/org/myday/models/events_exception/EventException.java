package org.myday.models.events_exception;

import jakarta.persistence.*;
import lombok.*;
import org.myday.models.recurring_event.RecurringEvent;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recurring_events_exceptions")
public class EventException {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurring_event_id", nullable = false)
    private RecurringEvent recurringEvent;

    @Column(name = "exception_time", nullable = false)
    private Instant exceptionTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventException that = (EventException) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
