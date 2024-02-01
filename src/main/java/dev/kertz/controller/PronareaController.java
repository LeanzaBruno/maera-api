package dev.kertz.controller;

import dev.kertz.core.ReportDownloader;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.model.Fir;
import dev.kertz.model.Pronarea;
import dev.kertz.repository.FirRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/pronarea", produces = "application/json" )
public class PronareaController {
    private final FirRepository firRepository;

    PronareaController(FirRepository firRepository){
        this.firRepository = firRepository;
    }

    @GetMapping("/{fir}")
    public Pronarea getPronarea(@PathVariable String fir){
        Fir firObj = firRepository.findByIdentifierIgnoreCase(fir).orElseThrow( () -> new FirNotFoundException(fir));
        return new Pronarea( ReportDownloader.getPronarea(firObj) ) ;
    }

}
