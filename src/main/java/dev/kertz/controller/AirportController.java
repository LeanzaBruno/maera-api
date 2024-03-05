package dev.kertz.controller;

import java.util.List;
import dev.kertz.dto.AirportDTO;
import dev.kertz.dto.AirportMapper;
import dev.kertz.model.Runway;
import dev.kertz.repository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;

@RestController
@RequestMapping(path = "/airports", produces = "application/json")
class AirportController{

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private RunwayRepository runwayRepository;

	@GetMapping
	public List<Airport> getAirports(){
		return airportRepository.findAll();
	}
	
	@GetMapping("/{code}")
	public AirportDTO getAirport(@PathVariable String code) {
		Airport airport = airportRepository.findByICAOIgnoreCase(code).orElseThrow( () -> new AirportNotFoundException(code));
		List<Runway> runways = runwayRepository.findByAirport(airport);
		return AirportMapper.toDTO(airport, runways);
	}

}