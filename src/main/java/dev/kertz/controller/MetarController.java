package dev.kertz.controller;

import dev.kertz.dto.ReportDTO;
import dev.kertz.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( path = "/metar", produces = "application/json")
public class MetarController {

	@Autowired
	private ReportService reportDownloader;

	@GetMapping("/{icao}")
	public ReportDTO getMetar(@PathVariable String icao){
		return reportDownloader.getMetar(icao);
	}
}
