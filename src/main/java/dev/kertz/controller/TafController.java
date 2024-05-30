package dev.kertz.controller;

import dev.kertz.dto.TAF;
import dev.kertz.service.ReportProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/taf", produces = "application/json" )
public class TafController {
	@Autowired
	private ReportProviderService reportsProvider;


	@GetMapping("/{icao}")
	public TAF getTAF(@PathVariable String icao) {
		return reportsProvider.getTAF(icao);
	}
}
