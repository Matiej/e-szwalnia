package com.eszwalnia.timesh.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionHandlerReponse {

    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
