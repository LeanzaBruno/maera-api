package dev.kertz.controller;

import java.util.List;
import dev.kertz.dto.AirportDTO;
import dev.kertz.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/airports", produces = "application/json")
class AirportController{

	@Autowired
	private AirportService airportService;

	@GetMapping
	public List<AirportDTO> getAirports(){
		return airportService.getAirports();
	}
	
	@GetMapping("/{icao}")
	public AirportDTO getAirport(@PathVariable String icao) {
		return airportService.getAirport(icao);
	}
}