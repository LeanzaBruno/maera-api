package dev.kertz.enums;

import lombok.Getter;

@Getter
public enum WeatherPhenomena{
    DZ("Llovizna"),
    RA("Lluvia"),
    SN("Nieve"),
    PL("Hielo granulado"),
    GR("Granizo"),
    GS("Granizo pequeño"),
    BR("Neblina"),
    FG("Niebla"),
    FU("Humo"),
    VA("Ceniza volcánica"),
    DU("Polvo"),
    SA("Arena"),
    HZ("Bruma"),
    PO("Remolinos de polvo"),
    SQ("Turbonadas"),
    FC("Nube con forma de embudo"),
    SS("Tempestad de arena"),
    DS("Tempestad de polvo"),
    TS("Tormenta");

    private final String meaning;

    private WeatherPhenomena(String meaning){
        this.meaning = meaning;
    }

}
