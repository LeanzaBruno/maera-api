package dev.kertz.controller;

import dev.kertz.dto.PRONAREA;
import dev.kertz.service.ReportProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/pronarea", produces = "application/json" )
public class PronareaController {

    @Autowired
    private ReportProviderService reportProvider;

    @GetMapping("/{firIcao}")
    public PRONAREA getPronarea(@PathVariable String firIcao){
        return reportProvider.getPRONAREA(firIcao);
    }
}
