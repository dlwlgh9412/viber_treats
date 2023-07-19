package com.sharetreats01.viber_chatbot.callback.advice;

import com.sharetreats01.viber_chatbot.viber.exception.ViberException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(ViberException.class)
    public void viberException() {
    }
}
