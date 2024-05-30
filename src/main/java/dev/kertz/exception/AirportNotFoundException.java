package dev.kertz.exception;

public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException(String code) {
		super("Couldn't find airport with code " + code);
	}

}