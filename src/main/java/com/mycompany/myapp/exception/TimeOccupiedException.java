package com.mycompany.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Расписание пересекается с уже существующим")
public class TimeOccupiedException extends RuntimeException {
    public TimeOccupiedException(String message) {
        super(message);
    }
}
