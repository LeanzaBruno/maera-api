package dev.kertz.controller;

import dev.kertz.core.ReportDownloader;
import dev.kertz.dto.Mapper;
import dev.kertz.dto.PronareaDTO;
import dev.kertz.exception.FirNotFoundException;
import dev.kertz.model.Fir;
import dev.kertz.model.Pronarea;
import dev.kertz.repository.FirRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( path = "/pronarea", produces = "application/json" )
public class PronareaController {
    private final FirRepository firRepository;

    PronareaController(FirRepository firRepository){
        this.firRepository = firRepository;
    }

    @GetMapping("/{fir}")
    public PronareaDTO getPronarea(@PathVariable String fir){
        Fir firObj = firRepository.findByIdentifierIgnoreCase(fir).orElseThrow( () -> new FirNotFoundException(fir));
        Pronarea pronarea = ReportDownloader.getPronarea(firObj);
        // TODO Guardar pronarea en repositorio pronareaRepository.save(pronarea)
        return Mapper.toDTO(pronarea);
    }

    @GetMapping("/all")
    public List<PronareaDTO> getAll(){
        List<PronareaDTO> dtos = new ArrayList<>();
        firRepository.findAll().forEach( fir -> dtos.add(Mapper.toDTO(ReportDownloader.getPronarea(fir))));
        return dtos;
    }

}
