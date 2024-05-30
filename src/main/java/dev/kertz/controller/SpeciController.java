package dev.kertz.controller;

import dev.kertz.dto.SPECI;
import dev.kertz.service.ReportProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( path = "/speci", produces = "application/json")
public class SpeciController {

    @Autowired
    private ReportProviderService reportsProvider;


    @GetMapping("/{icao}")
    public SPECI getSPECI(@PathVariable String icao){
        return reportsProvider.getSPECI(icao);
    }

    @GetMapping("/all")
    public List<SPECI> getCurrentsSpeci(){
        return reportsProvider.getAllSPECI();
    }
}