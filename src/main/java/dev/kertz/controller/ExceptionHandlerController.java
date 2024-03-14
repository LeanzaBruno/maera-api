package dev.kertz.controller;

import dev.kertz.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({
            AirportNotFoundException.class,
            FirNotFoundException.class,
            ReportNotFoundException.class})
    public ResponseEntity<APIError> handleNotFoundException(Exception exception, HttpServletRequest request) {
        APIError error = new APIError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ResponseBody
    @ExceptionHandler(InvalidTafStationException.class)
    public ResponseEntity<APIError> handleInvalidTafStation(InvalidTafStationException exception, HttpServletRequest request){
        APIError error = new APIError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<APIError> connectionProblem(IOException exception, HttpServletRequest request) {
        APIError error = new APIError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
