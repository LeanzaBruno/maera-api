package dev.kertz.exception;


public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String type){
        super(type + " was not found.");
    }
}
