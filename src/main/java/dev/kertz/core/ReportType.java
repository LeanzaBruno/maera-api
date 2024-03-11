package dev.kertz.core;

import lombok.Getter;

@Getter
enum ReportType {
	METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar"),
	SPECI("https://ssl.smn.gob.ar/mensajes/index.php?observacion=speci&operacion=consultar"),
	TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar"),
	PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar");
	
	private final String url;
	ReportType(String url) {
		this.url = url;
	}
}
	