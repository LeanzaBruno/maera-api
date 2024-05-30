package dev.kertz.exception;


public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String type, String icao){
        super(type + " from " + icao.toUpperCase() + " was not found.");
    }
}
