package dev.kertz.exception;

import dev.kertz.model.Airport;

public class InvalidTafStationException extends RuntimeException{

    public InvalidTafStationException(Airport airport){
        super("Error: Airport " + airport.getICAO() + " station doesn't provide taf reports");
    }
}
