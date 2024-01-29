package dev.kertz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import dev.kertz.exception.AirportNotFoundException;

@ControllerAdvice
public class AirportNotFoundController {

	@ResponseBody
	@ExceptionHandler(AirportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String airportNotFound(AirportNotFoundException exception) {
		return exception.getMessage();
	}


}
