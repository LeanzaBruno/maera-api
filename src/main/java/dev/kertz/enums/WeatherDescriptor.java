package dev.kertz.enums;

import lombok.Getter;

@Getter
public enum WeatherDescriptor{
    MI("baja"),
    BC("bancos"),
    PR("parcial"),
    DR("ventisca baja"),
    BL("ventisca alta"),
    SH("chubascos"),
    TS("tormenta"),
    FZ("engelante"),
    RE("reciente");

    private String meaning;

    private WeatherDescriptor(String meaning){
        this.meaning = meaning;
    }

}
