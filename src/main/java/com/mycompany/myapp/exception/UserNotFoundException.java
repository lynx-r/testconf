package com.mycompany.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Пользователь не найден")
public class UserNotFoundException extends RuntimeException {
}
