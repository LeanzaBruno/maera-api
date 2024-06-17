package dev.kertz.controller;

import dev.kertz.dto.SIGMET;
import dev.kertz.repository.FirRepository;
import dev.kertz.service.ReportProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/sigmet", produces = "application/json")
public class SigmetController {

    @Autowired
    private FirRepository firRepository;

    @Autowired
    private ReportProviderService reportProvider;


    @GetMapping("/{firIcao}")
    public SIGMET getSIGMET(@PathVariable String firIcao){
        return reportProvider.getSIGMET(firIcao);
    }


    @GetMapping("/all")
    public List<SIGMET> getAllSIGMET(){
        return reportProvider.getAllSIGMET();
    }
}
