package dev.xalpol12.wheretoeatbackend.exception;

import com.google.maps.errors.ApiException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlacesControllerExceptionHandlerTest {

    private static PlacesControllerExceptionHandler exceptionHandler;
    @BeforeAll
    static void startup() {
        exceptionHandler = new PlacesControllerExceptionHandler();
    }

    @Test
    void handleIOException() {
        //given
        IOException ioException = new IOException();

        //when
        ResponseEntity<Object> response = exceptionHandler.handleIOException(ioException);

        //then
        System.out.println(response);
        assertEquals(response.getStatusCode(), HttpStatus.GATEWAY_TIMEOUT);
    }

    @Test
    void handleInterruptedException() {
        //given
        InterruptedException interruptedException = new InterruptedException();

        //when
        ResponseEntity<Object> response = exceptionHandler.handleInterruptedException(interruptedException);

        //then
        System.out.println(response);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void handleApiException() {
        //given
        ApiException apiException = ApiException.from("OVER_QUERY_LIMIT", "Error message generated by Google Places API");

        //when
        ResponseEntity<Object> response = exceptionHandler.handleApiException(apiException);

        //then
        System.out.println(response);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}