package org.myday.models.recurring_event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingRecurringEvent {

    @Schema(description = "id события", example = "894fea01-cfd1-4883-849d-b88dacf8bf4e")
    private UUID id;
    private String title;
    private String rrule;
    private String timeZone;
    private Instant startTime;
    private Instant endTime;
    private Boolean isDeleted;
    private Instant createdAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OutgoingRecurringEvent that = (OutgoingRecurringEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
