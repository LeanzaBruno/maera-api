package dev.kertz.core;

enum WeatherBriefing {
	METAR("https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO="),
	TAF("https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar&tipoEstacion=OACI&CODIGO="),
	PRONAREA("https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar&??=on");
	
	public String url;
	WeatherBriefing(String url) {
		this.url = url;
	}
}
	