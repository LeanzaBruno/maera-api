package dev.kertz.exception;

public class AirportNotFoundException extends RuntimeException{

	public AirportNotFoundException(String code) {
		super("Couldn't found airport with code " + code);
	}

}