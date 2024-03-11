package dev.kertz.exception;

public class FirNotFoundException extends RuntimeException {
	public FirNotFoundException(String firId) {
		super("There is no fir with name " + firId);
	}
}
