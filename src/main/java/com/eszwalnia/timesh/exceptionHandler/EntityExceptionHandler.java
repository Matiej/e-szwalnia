package com.eszwalnia.timesh.exceptionHandler;

import com.eszwalnia.timesh.authUser.AuthUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RestControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionHandlerReponse exceptionResponse = getExceptionHandlerReponse(ex, request);
        log.error("error message details ==> ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(AuthUserNotFoundException.class)
    public final ResponseEntity<Object> handleAuthUserNotFound(Exception ex, WebRequest webRequest) {
        log.error("error message details ==> ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getExceptionHandlerReponse(ex, webRequest));
    }

    @ExceptionHandler(ExistEmailException.class)
    public final ResponseEntity<Object> handleEmailExistException(RuntimeException rex, WebRequest webRequest) {
        log.error("error message details ==> ", rex);
        return ResponseEntity.status(409).body(getExceptionHandlerReponse(rex,webRequest));
    }

    @ExceptionHandler(HibernateException.class)
    public final ResponseEntity<Object> handleHibernateException(RuntimeException rex, WebRequest webRequest) {
        log.error("error message details ==> ", rex);
        return ResponseEntity.status(503).body(getExceptionHandlerReponse(rex, webRequest));
    }

    private ExceptionHandlerReponse getExceptionHandlerReponse(Exception ex, WebRequest request) {
        return new ExceptionHandlerReponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
    }
}
