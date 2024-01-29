package dev.kertz.core;

enum WeatherReport {
	METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO="),
	TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar&tipoEstacion=OACI&CODIGO="),
	PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar&tipoEstacion=OACI&CODIGO=");
	
	public String url;
	WeatherReport(String url) {
		this.url = url;
	}
}
	