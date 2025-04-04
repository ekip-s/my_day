package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myday.models.recurring_event.IncomingRecurringEvent;
import org.myday.models.recurring_event.OutgoingRecurringEvent;
import org.myday.services.recurring_event.RecurringEventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recurring_events/api/v1")
@Tag(name="RecurringEvents", description = "Управление рекурентными событиями")
public class RecurringEventsController {

    private final RecurringEventsService recurringEventsService;

    @Operation(
            summary = "Получение события по id"
    )
    @GetMapping("/{eventId}")
    public OutgoingRecurringEvent getRecurringEventById(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId) {
        log.info("GET: RecurringEventsController getRecurringEventById, id: {}", eventId);
        return recurringEventsService.getRecurringEventById(eventId);
    }

    @Operation(
            summary = "Получение событий пользователя"
    )
    @GetMapping
    public List<OutgoingRecurringEvent> getUserRecurringEvents() {
        log.info("GET: RecurringEventsController getUserRecurringEvents");
        return recurringEventsService.getUserRecurringEvents();
    }

    @Operation(
            summary = "Создание нового события"
    )
    @PostMapping
    public OutgoingRecurringEvent addRecurringEvent(@RequestBody IncomingRecurringEvent incomingRecurringEvent) {
        log.info("POST: RecurringEventsController addRecurringEvent, параметры: {}", incomingRecurringEvent);
        return recurringEventsService.addRecurringEvent(incomingRecurringEvent);
    }

    @Operation(
            summary = "Изменение события"
    )
    @PutMapping
    public OutgoingRecurringEvent updateRecurringEvent(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId,
            @RequestBody IncomingRecurringEvent incomingRecurringEvent) {
        log.info("PUT: RecurringEventsController updateRecurringEvent, eventId: {}, параметры: {}",
                eventId, incomingRecurringEvent);
        return recurringEventsService.updateRecurringEvent(eventId, incomingRecurringEvent);
    }

    @Operation(
            summary = "Приостановка события"
    )
    @PatchMapping("/{eventId}")
    public OutgoingRecurringEvent pauseRecurringEvent(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId,
            @RequestParam @Parameter(description = "Удалено?", required = true) Boolean isDeleted) {
        log.info("PATCH: RecurringEventsController pauseRecurringEvent, eventId: {}, isDeleted: {}",
                eventId, isDeleted);
        return recurringEventsService.pauseRecurringEvent(eventId, isDeleted);
    }

    @DeleteMapping("/{eventId}")
    public void deleteRecurringEventById(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId) {
        log.info("DELETE: RecurringEventsController deleteRecurringEventById, id: {}", eventId);
        recurringEventsService.deleteRecurringEventById(eventId);
    }
}
