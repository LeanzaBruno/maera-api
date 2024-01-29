package dev.kertz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import dev.kertz.exception.FirNotFoundException;

@ControllerAdvice
public class FirNotFoundController {
	
	@ResponseBody
	@ExceptionHandler(FirNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String error(FirNotFoundException exception) {
		return exception.getMessage();
	}

}
