package dev.kertz.controller;

import dev.kertz.dto.MetarMapper;
import dev.kertz.dto.ReportDTO;
import dev.kertz.exception.ReportNotFoundException;
import dev.kertz.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;

@RestController
@RequestMapping( path = "/metar", produces = "application/json")
public class MetarController {

	@Autowired
	private AirportRepository airportRepository;

	@GetMapping("/{icao}")
	public ReportDTO getMetar(@PathVariable String icao){
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
		Report metar = ReportDownloader.getMetar(airport).orElseThrow( () -> new ReportNotFoundException("METAR"));
		return MetarMapper.toDTO(metar);
	}
}
