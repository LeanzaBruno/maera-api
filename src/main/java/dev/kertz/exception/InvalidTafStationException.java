package dev.kertz.exception;

import dev.kertz.model.Airport;

public class InvalidTafStationException extends RuntimeException{

    public InvalidTafStationException(Airport airport){
        super("Airport " + airport.getICAO() + " doesn't provide taf reports");
    }
}
