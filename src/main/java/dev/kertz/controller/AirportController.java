package dev.kertz.controller;

import java.util.List;
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

	private AirportRepository repository;
	
	public AirportController(AirportRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Airport> getAirports(){
		return repository.findAll();
	}
	
	@GetMapping("/{code}")
	public Airport getAirport(@PathVariable String code) {
		return repository.findByICAO(code.toUpperCase()).orElseThrow( () -> new AirportNotFoundException(code));
	}

}