package org.myday.services.aggregated_data;

import org.myday.models.aggregated_data.AggregatedNode;

import java.time.LocalDate;
import java.util.List;

public interface AggregatedDataService {

    List<AggregatedNode> getDayPlan(LocalDate date);
}
