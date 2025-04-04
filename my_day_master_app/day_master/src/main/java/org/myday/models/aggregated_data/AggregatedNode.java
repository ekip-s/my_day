package org.myday.models.aggregated_data;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedNode {

    private Integer id;
    private String title;
    private Instant startTime;
    private Instant endTime;
    private Boolean isDeleted;
    private EventType type;
}
