package dev.kertz.controller;


import dev.kertz.dto.ReportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/sigmet", produces = "application/json")
public class SigmetController {



    @GetMapping
    public List<ReportDTO> getCurrentSigmets(){
        return null;
    }
}
