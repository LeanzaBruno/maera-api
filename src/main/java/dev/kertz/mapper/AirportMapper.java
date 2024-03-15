package dev.kertz.mapper;

import dev.kertz.dto.AirportDTO;
import dev.kertz.model.Airport;

/**
 * Maps an Airport into a DTO
 */
public class AirportMapper {

    public static AirportDTO toDTO(Airport airport){
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
                airport.getRunways().stream().map(RunwayMapper::toDTO).toList()
        );
    }
}
