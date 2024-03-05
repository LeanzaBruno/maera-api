package dev.kertz.dto;

import java.util.List;

public record AirportDTO(String ICAO, String IATA, String ANAC, int WMO, String name, String city, boolean hasTAF,
                        int elevation, float latitude, float longitude, String province, String fir, List<RunwayDTO> runways){ }
