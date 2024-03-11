package dev.kertz.controller;

import dev.kertz.core.ReportDownloader;
import dev.kertz.dto.MetarMapper;
import dev.kertz.dto.ReportDTO;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.exception.ReportNotFoundException;
import dev.kertz.model.Airport;
import dev.kertz.model.Report;
import dev.kertz.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/speci", produces = "application/json")
public class SpeciController {

    @Autowired
    private AirportRepository airportRepository;


    @GetMapping("/{icao}")
    public ReportDTO getSpeci(@PathVariable String icao){
        Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
        Report speci = ReportDownloader.getSpeci(airport).orElseThrow( () -> new ReportNotFoundException("SPECI"));
        return MetarMapper.toDTO(speci);
    }
}
