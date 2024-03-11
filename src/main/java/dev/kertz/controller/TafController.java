package dev.kertz.controller;

import dev.kertz.dto.ReportDTO;
import dev.kertz.dto.TafMapper;
import dev.kertz.exception.InvalidTafStationException;
import dev.kertz.exception.ReportNotFoundException;
import dev.kertz.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;

@RestController
@RequestMapping( path = "/taf", produces = "application/json" )
public class TafController {

	@Autowired
	private AirportRepository airportRepository;


	@GetMapping("/{icao}")
	public ReportDTO getTaf(@PathVariable String icao) {
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));

		if( ! airport.isTafStation())
			throw new InvalidTafStationException(airport);

		Report taf = ReportDownloader.getTaf(airport).orElseThrow(() -> new ReportNotFoundException("TAF"));
		return TafMapper.toDTO(taf);
	}
}
