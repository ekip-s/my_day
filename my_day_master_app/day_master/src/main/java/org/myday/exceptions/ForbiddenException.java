package org.myday.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForbiddenException extends RuntimeException {

    private final String reason;
    private final LocalDateTime timestamp;

    public ForbiddenException(String message, String reason) {
        super(message);
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }
}
