package dev.kertz.controller;

import java.util.List;
import dev.kertz.dto.MetarDTO;
import dev.kertz.dto.MetarMapper;
import dev.kertz.model.Metar;
import org.springframework.web.bind.annotation.*;
import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.model.Fir;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;
import static java.util.stream.Collectors.toList;

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

	@GetMapping
	public MetarDTO getMetar(@RequestParam(required = false) String code, @RequestParam(required = false) String fir){
		Airport airport = airportRepository.findByICAOIgnoreCase(code).orElseThrow( () -> new AirportNotFoundException(code));
		Metar metar = ReportDownloader.getMetars( List.of(airport) ).get(0);
		// TODO metarRepository.save(metar);
		return MetarMapper.toDTO(metar);
	}

	
	@GetMapping("/fir")
	public List<MetarDTO> getMetarsByFir(@RequestParam String fir){
		Fir firObj = firRepository.findByIdentifierIgnoreCase(fir).orElseThrow( () -> new FirNotFoundException(fir) );
		List<Airport> airports = airportRepository.findByFir(firObj);
		List<Metar> metars = ReportDownloader.getMetars(airports);
		// TODO metarRepository.save(metars)
		return metars.stream().map(MetarMapper::toDTO).collect(toList());
	}
}
