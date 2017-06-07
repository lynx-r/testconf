package com.mycompany.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Доклад не найден")
public class PresentationNotFoundException extends RuntimeException {
}
