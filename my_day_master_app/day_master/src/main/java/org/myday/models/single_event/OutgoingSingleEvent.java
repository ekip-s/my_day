package org.myday.models.single_event;

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
public class OutgoingSingleEvent {

    @Schema(description = "id события", example = "894fea01-cfd1-4883-849d-b88dacf8bf4e")
    private UUID id;
    @Schema(description = "Заголовок", example = "Почистить зубы")
    private String title;
    @Schema(description = "Начало события")
    private Instant startTime;
    @Schema(description = "Окончание события")
    private Instant endTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OutgoingSingleEvent that = (OutgoingSingleEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
