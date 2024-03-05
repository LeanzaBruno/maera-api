package dev.kertz.dto;

import dev.kertz.model.Airport;
import dev.kertz.model.Runway;

import java.util.List;
import java.util.stream.Stream;

public class AirportMapper {

    public static AirportDTO toDTO(Airport airport, List<Runway> runways){
        List<RunwayDTO> runwaysDTO = runways.stream().map(RunwayMapper::toDTO).toList();
        return new AirportDTO(
                airport.getICAO(),
                airport.getIATA(),
                airport.getAnac(),
                airport.getWMO(),
                airport.getName(),
                airport.getCity(),
                airport.isHasTAF(),
                airport.getElevation(),
                airport.getLatitude(),
                airport.getLongitude(),
                airport.getProvince(),
                airport.getFir(),
                runwaysDTO
        );
    }
}
