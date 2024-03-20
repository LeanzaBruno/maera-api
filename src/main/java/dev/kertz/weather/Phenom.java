package dev.kertz.weather;

public enum Phenom {
    DZ("Lloviznas"),
    RA("LLuvias"),
    SN("Nieve"),
    PL("Hielo granulado"),
    GR("Granizo"),
    GS("Granizo pequeño o nieve granulada"),
    UP("Precipitación desconodida"),
    BR("Neblina"),
    FG("Niebla"),
    FU("Humo"),
    DU("Polvo"),
    SA("Arena"),
    HZ("Bruma"),
    PO("Remolinos de polvo"),
    SQ("Turbonadas"),
    FC("Nube en forma de embudo"),
    SS("Tempestad de arena"),
    DS("Tempestad de polvo"),
    NSW("No Significant Weather (Ningún fenómeno significativo)");

    private final String description;

    Phenom(String description){
        this.description = description;
    }

    public String description(){
        return description;
    }
}
