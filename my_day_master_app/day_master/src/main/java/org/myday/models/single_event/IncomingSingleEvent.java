package org.myday.models.single_event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IncomingSingleEvent {

    @Schema(description = "Заголовок", example = "Почистить зубы")
    private String title;
    @Schema(description = "Начало события")
    private Instant startTime;
    @Schema(description = "Окончание события")
    private Instant endTime;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IncomingSingleEvent that = (IncomingSingleEvent) o;
        return Objects.equals(title, that.title) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startTime, endTime);
    }
}
