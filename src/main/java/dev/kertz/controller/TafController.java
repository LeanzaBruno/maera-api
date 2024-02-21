package dev.kertz.controller;

import java.util.List;
import dev.kertz.dto.ReportDTO;
import dev.kertz.dto.TafMapper;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.model.Fir;
import dev.kertz.model.Taf;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping( path = "/taf", produces = "application/json" )
public class TafController {
	
	private final AirportRepository airportRepository;
	private final FirRepository firRepository;
	
	public TafController(AirportRepository airportRepository, FirRepository firRepository) {
		this.airportRepository = airportRepository;
		this.firRepository = firRepository;
	}
	

	@GetMapping("/airports/{icao}")
	public ReportDTO getTaf(@PathVariable String icao) {
		Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
		Taf taf = ReportDownloader.getTafs( List.of(airport) ).getFirst();
		// TODO tafRepository.save(taf);
		return TafMapper.toDTO(taf);
	}
	
	@GetMapping("/fir/{fir}")
	public List<ReportDTO> getTafByFir(@PathVariable String fir){
		Fir firObj = firRepository.findByIdentifierIgnoreCase(fir).orElseThrow( () -> new FirNotFoundException(fir) );
		List<Airport> airports = airportRepository.findByFir(firObj);
		List<Taf> tafs = ReportDownloader.getTafs(airports);
		// TODO tafRepository.save(tafs)
		return tafs.stream().map(TafMapper::toDTO).collect(toList());
	}
}
