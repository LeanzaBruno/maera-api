package dev.kertz.controller;

import dev.kertz.dto.METAR;
import dev.kertz.service.ReportProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( path = "/metar", produces = "application/json")
public class MetarController {

	@Autowired
	private ReportProviderService reportProvider;

	@GetMapping("/{icao}")
	public METAR getMETAR(@PathVariable String icao){
		return reportProvider.getMETAR(icao);
	}
}
