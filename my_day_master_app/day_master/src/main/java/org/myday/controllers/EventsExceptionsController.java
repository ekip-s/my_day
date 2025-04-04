package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myday.services.event_exceptions.EventsExceptionsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events_exceptions/api/v1")
@Tag(name="EventsExceptions", description = "Управление исключениями событий")
public class EventsExceptionsController {

    private final EventsExceptionsService eventsExceptionsService;

    @Operation(
            summary = "Добавить исключение для рекурентного события"
    )
    @PostMapping("/{eventId}")
    public void addEventException(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId,
            @RequestParam @Parameter(description = "Дата/время исключения?", required = true) Instant exceptionTime
    ) {
        log.info("POST: EventsExceptionsController addEventException, eventId: {}, exceptionTime: {}",
                eventId, exceptionTime);
        eventsExceptionsService.addEventException(eventId, exceptionTime);
    }

    @Operation(
            summary = "Удаление исключения по id"
    )
    @DeleteMapping("/{exceptionId}")
    public void deleteEventExceptionById(
            @PathVariable @Parameter(description = "id события", required = true) UUID exceptionId) {
        log.info("DELETE: EventsExceptionsController deleteEventExceptionById, exceptionId: {}",
                exceptionId);
        eventsExceptionsService.deleteEventExceptionById(exceptionId);
    }
}
