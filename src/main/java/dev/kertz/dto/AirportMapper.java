package dev.kertz.dto;

import dev.kertz.model.Airport;
import dev.kertz.model.Runway;
import java.util.List;

/**
 * Maps an Airport into a DTO
 */
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
                airport.isTafStation(),
                airport.getElevation(),
                airport.getLatitude(),
                airport.getLongitude(),
                airport.getProvince().getName(),
                airport.getFir().getId(),
                runwaysDTO
        );
    }
}
