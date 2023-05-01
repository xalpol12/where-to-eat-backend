package dev.xalpol12.wheretoeatbackend.exception;

import com.google.maps.errors.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice(annotations = RestController.class)
public class PlacesControllerExceptionHandler {

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIOException(IOException exception) {
        return new ResponseEntity<>(exception, HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(InterruptedException.class)
    protected ResponseEntity<Object> handleInterruptedException(InterruptedException exception) {
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(ApiException exception) {
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
