package org.myday.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events_exceptions/api/v1")
@Tag(name="EventsExceptions", description = "Управление исключениями событий")
public class EventsExceptionsController {

    //создание исключения;
    @Operation(
            summary = "Добавить исключение для рекурентного события"
    )
    @PostMapping
    public void addEventException() {

    }

    @Operation(
            summary = "Удаление исключения по id"
    )
    @DeleteMapping
    public void deleteEventExceptionById(UUID exceptionId) {

    }
}
