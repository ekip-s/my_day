package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recurring_events/api/v1")
@Tag(name="RecurringEvents", description = "Управление рекурентными событиями")
public class RecurringEventsController {

    @Operation(
            summary = "Получение события по id"
    )
    @GetMapping("/{eventId}")
    public void getRecurringEventById(@PathVariable UUID eventId) {

    }

    @Operation(
            summary = "Получение событий пользователя"
    )
    @GetMapping
    public void getUserRecurringEvents() {

    }

    @Operation(
            summary = "Создание нового события"
    )
    @PostMapping
    public void addRecurringEvent() {

    }

    @Operation(
            summary = "Изменение события"
    )
    @PutMapping
    public void updateRecurringEvent() {

    }

    @Operation(
            summary = "Приостановка события"
    )
    @PatchMapping("/{eventId}")
    public void pauseRecurringEvent(@PathVariable UUID eventId, @RequestParam Boolean isDeleted) {

    }

    @DeleteMapping("/{eventId}")
    public void deleteRecurringEventById(@PathVariable UUID eventId) {

    }
}
