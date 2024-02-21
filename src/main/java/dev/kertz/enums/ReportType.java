package dev.kertz.enums;

import lombok.Getter;

@Getter
public enum ReportType {
    METAR("Informe meteorológico ordinario del aeródromo."),
    METAR_NIL("Informe meteorológico faltante."),
    SPECI("Informe meteorológico especial del aeródromo."),
    TAF("Pronóstico de aeródromo."),
    TAF_AMD("Pronóstico fue enmendado."),
    TAF_COR("Pronóstico fue corregido."),
    TAF_NIL("Pronóstico no será emitido.");

    private final String meaning;

    private ReportType(String meaning){
        this.meaning = meaning;
    }

    public String getName(){
        return name().replace('_', ' ');
    }

    public boolean isCompound(){
        return name().contains("_");
    }
}
