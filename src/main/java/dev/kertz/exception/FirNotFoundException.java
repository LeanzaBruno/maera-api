package dev.kertz.exception;

public class FirNotFoundException extends RuntimeException {
	
	public FirNotFoundException(String firCode) {
		super("ERROR: There is no fir with name " + firCode);
	}

}
