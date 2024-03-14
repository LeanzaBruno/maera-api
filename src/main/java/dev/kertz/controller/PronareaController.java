package dev.kertz.controller;

import dev.kertz.dto.ReportDTO;
import dev.kertz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/pronarea", produces = "application/json" )
public class PronareaController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/{firId}")
    public ReportDTO getPronarea(@PathVariable String firId){
        return reportService.getPronarea(firId);
    }
}
