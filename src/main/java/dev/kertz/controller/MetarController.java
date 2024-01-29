package dev.kertz.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.model.Fir;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;

@RestController
@RequestMapping( path = "/metar", produces = "application/json")
public class MetarController {
	
	private final AirportRepository airportRepository;
	private final FirRepository firRepository;
	
	MetarController(AirportRepository airportRepository,
					FirRepository firRepository
			){
		this.airportRepository = airportRepository;
		this.firRepository = firRepository;
	}
	

	@GetMapping("/{code}")
	public String getMetar(@PathVariable String code){
		Airport airport = airportRepository.findByICAOIgnoreCase(code).orElseThrow( () -> new AirportNotFoundException(code));
		return ReportDownloader.getMetar(airport);
	}
	
	
	@GetMapping("/fir/{fir}")
	public List<String> getMetarsByFir(@PathVariable String fir){
		Fir firObj = firRepository.findByCodeIgnoreCase(fir).orElseThrow( () -> new FirNotFoundException(fir) );
		List<Airport> airports = airportRepository.findByFir(firObj);
		return ReportDownloader.getMetars(airports);
	}
	
	
	
	
	

}