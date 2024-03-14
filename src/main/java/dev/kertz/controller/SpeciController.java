package dev.kertz.controller;

import dev.kertz.dto.ReportDTO;
import dev.kertz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/speci", produces = "application/json")
public class SpeciController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/{icao}")
    public ReportDTO getSpeci(@PathVariable String icao){
        return reportService.getSpeci(icao);
    }

    @GetMapping
    public List<ReportDTO> getCurrentSpeci(){
        return reportService.getCurrentSpeci();
    }
}