package org.myday.models.recurring_event;

import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IncomingRecurringEvent {

    private String title;
    private String rrule;
    private Boolean isDeleted;
    private String timeZone;
    private Instant startTime;
    private Instant endTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IncomingRecurringEvent that = (IncomingRecurringEvent) o;
        return Objects.equals(title, that.title) && Objects.equals(rrule, that.rrule) && Objects.equals(timeZone, that.timeZone) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rrule, timeZone, startTime, endTime);
    }
}
