package dev.kertz.controller;

import dev.kertz.core.ReportDownloader;
import dev.kertz.dto.PronareaMapper;
import dev.kertz.dto.ReportDTO;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.exception.ReportNotFoundException;
import dev.kertz.model.Fir;
import dev.kertz.model.Report;
import dev.kertz.repository.FirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/pronarea", produces = "application/json" )
public class PronareaController {

    @Autowired
    private FirRepository firRepository;


    @GetMapping("/{firId}")
    public ReportDTO getPronarea(@PathVariable String firId){
        Fir fir = firRepository.findByIdIgnoreCase(firId).orElseThrow( () -> new FirNotFoundException(firId));
        Report pronarea = ReportDownloader.getPronarea(fir).orElseThrow(() -> new ReportNotFoundException("PRONAREA"));
        return PronareaMapper.toDTO(pronarea);
    }
}
