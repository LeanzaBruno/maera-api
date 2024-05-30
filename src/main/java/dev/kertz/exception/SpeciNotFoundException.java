package dev.kertz.exception;

public class SpeciNotFoundException extends ReportNotFoundException{
    public SpeciNotFoundException(String icao){
        super("SPECI", icao);
    }
}
