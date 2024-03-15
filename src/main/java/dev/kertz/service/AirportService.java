package dev.kertz.service;

import dev.kertz.dto.AirportDTO;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.mapper.AirportMapper;
import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;


    public AirportDTO getAirport(@PathVariable String code) {
        Airport airport = airportRepository.findByICAOIgnoreCase(code).orElseThrow( () -> new AirportNotFoundException(code));
        return AirportMapper.toDTO(airport);
    }

    public List<AirportDTO> getAirports(){
        List<Airport> airports = airportRepository.findAll();
        return airports.stream().map(AirportMapper::toDTO).toList();
    }
}
