package dev.kertz.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IOExceptionController {
	
	@ResponseBody
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public String connectionProblem(IOException exception) {
		return exception.getMessage();
	}
	
}
