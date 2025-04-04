package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myday.models.aggregated_data.AggregatedNode;
import org.myday.services.aggregated_data.AggregatedDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/aggregated_data/api/v1")
@Tag(name="AggregatedData", description = "Получение агрегированных данных")
public class AggregatedDataController {

    private final AggregatedDataService aggregatedDataService;

    @Operation(
            summary = "Получение плана дня"
    )
    @GetMapping("/per_day/{date}")
    public List<AggregatedNode> getDayPlan(@PathVariable LocalDate date) {
        log.info("GET: AggregatedDataController getDayPlan, date: {}", date);
        return aggregatedDataService.getDayPlan(date);
    }
}
