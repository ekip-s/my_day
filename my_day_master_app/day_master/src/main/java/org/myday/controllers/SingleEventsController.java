package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myday.models.single_event.IncomingSingleEvent;
import org.myday.models.single_event.OutgoingSingleEvent;
import org.myday.services.single_event.SingleEventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/single_events/api/v1")
@Tag(name="SingleEvents", description = "Управление уникальными событиями")
public class SingleEventsController {

    private final SingleEventsService singleEventsService;

    @Operation(
            summary = "Получение события по id"
    )
    @GetMapping("/{eventId}")
    public OutgoingSingleEvent getSingleEventById(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId) {
        log.info("GET: SingleEventsController getSingleEventById, id: {}", eventId);
        return singleEventsService.getSingleEventById(eventId);
    }

    @Operation(
            summary = "Получение событий пользователя"
    )
    @GetMapping
    public List<OutgoingSingleEvent> getUserSingleEvent() {
        log.info("GET: SingleEventsController getUserSingleEvent");
        return singleEventsService.getUserSingleEvent();
    }

    @Operation(
            summary = "Создание нового события"
    )
    @PostMapping
    public OutgoingSingleEvent addSingleEvents(@RequestBody IncomingSingleEvent incomingEvent) {
        log.info("POST: SingleEventsController addSingleEvents, параметры: {}", incomingEvent);
        return singleEventsService.addSingleEvents(incomingEvent);
    }

    @Operation(
            summary = "Изменение события"
    )
    @PutMapping("/{eventId}")
    public OutgoingSingleEvent updateSingleEvents(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId,
            @RequestBody IncomingSingleEvent incomingEvent) {
        log.info("PUT: SingleEventsController updateSingleEvents, id = {}, параметры: {}",
                eventId, incomingEvent);
        return singleEventsService.updateSingleEvents(eventId, incomingEvent);
    }

    @Operation(
            summary = "Удалить событие по Id"
    )
    @DeleteMapping("/{eventId}")
    public void deleteSingleEventById(
            @PathVariable @Parameter(description = "id события", required = true) UUID eventId) {
        log.info("DELETE: SingleEventsController deleteSingleEventById, id: {}", eventId);
        singleEventsService.deleteSingleEventById(eventId);
    }
}
