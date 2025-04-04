package org.myday.services.aggregated_data;

import org.myday.models.aggregated_data.AggregatedNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AggregatedDataServiceImpl implements AggregatedDataService {
    @Override
    public List<AggregatedNode> getDayPlan(LocalDate date) {
        return List.of();
    }
}
