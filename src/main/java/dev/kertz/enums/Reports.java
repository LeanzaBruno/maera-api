package dev.kertz.enums;

import lombok.Getter;

public class Reports{

    @Getter
    public enum Type {
        METAR("METeorological Aerodrome Report (Informe meteorológico ordinario del aeródromo)",
                "https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar"),
        SPECI("SPECI es un tipo de METAR emitido excepcionalmente cuando las condiciones cambian drásticamente",
                "https://ssl.smn.gob.ar/mensajes/index.php?observacion=speci&operacion=consultar"),
        TAF("Terminal Aerodrome Forecast (Pronóstico de aeródromo) es un pronóstico meteorológico para un aeropuerto en específico",
                "https://ssl.smn.gob.ar/mensajes/index.php?observacion=taf&operacion=consultar"),
        PRONAREA("Pronóstico de área", "https://ssl.smn.gob.ar/mensajes/index.php?observacion=pronarea&operacion=consultar"),
        SIGMET("SIGnificant METeorology (Meteorología significativa) es un reporte emitido para una área de información de vuelo (FIR)",
                "https://ssl.smn.gob.ar/mensajes/index.php?observacion=sigmet&operacion=consultar");

        private final String meaning;
        private final String url;

        Type(String meaning, String url){
            this.meaning = meaning;
            this.url = url;
        }
    }

    @Getter
    public enum Postfix{
        AMD("enmendado"),
        COR("corregido"),
        NIL("faltante");

        private final String meaning;

        Postfix(String meaning){
            this.meaning = meaning;
        }
    }

}
