package dev.kertz.service;

import dev.kertz.dto.Decoding;
import dev.kertz.dto.ReportDTO;
import dev.kertz.exception.AirportNotFoundException;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.exception.InvalidTafStationException;
import dev.kertz.exception.ReportNotFoundException;
import dev.kertz.mapper.PronareaMapper;
import dev.kertz.mapper.ReportMapper;
import dev.kertz.model.Airport;
import dev.kertz.model.Fir;
import dev.kertz.model.Report;
import dev.kertz.repository.AirportRepository;
import dev.kertz.repository.FirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportDownloaderService downloader;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FirRepository firRepository;

    @Autowired
    private ReportDecoderService decoder;

    public ReportDTO getMetar(String icao){
        Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
        Report metar = downloader.getMetar(airport).orElseThrow( () -> new ReportNotFoundException("METAR"));
        List<Decoding> decodings = decoder.decode(metar);
        return ReportMapper.makeDTO(metar, decodings);
    }

    public ReportDTO getTaf(String icao) {
        Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));

        if( ! airport.isTafStation())
            throw new InvalidTafStationException(airport);

        Report taf = downloader.getTaf(airport).orElseThrow(() -> new ReportNotFoundException("TAF"));
        List<Decoding> decodings = decoder.decode(taf);
        return ReportMapper.makeDTO( taf, decodings);
    }


    public ReportDTO getPronarea(@PathVariable String firId){
        Fir fir = firRepository.findByIdIgnoreCase(firId).orElseThrow( () -> new FirNotFoundException(firId));
        Report pronarea = downloader.getPronarea(fir).orElseThrow(() -> new ReportNotFoundException("PRONAREA"));
        return PronareaMapper.toDTO(pronarea);
    }


    public ReportDTO getSpeci(@PathVariable String icao){
        Airport airport = airportRepository.findByICAOIgnoreCase(icao).orElseThrow( () -> new AirportNotFoundException(icao));
        Report speci = downloader.getSpeci(airport).orElseThrow( () -> new ReportNotFoundException("SPECI"));
        List<Decoding> decodings = decoder.decode(speci);
        return ReportMapper.makeDTO(speci, decodings);
    }

    @GetMapping("/currents")
    public List<ReportDTO> getCurrentSpeci(){
        List<Report> specis = downloader.getSpecis(airportRepository.findAll());
        return specis.stream().map(speci -> ReportMapper.makeDTO(speci, decoder.decode(speci))).toList();
    }
}
