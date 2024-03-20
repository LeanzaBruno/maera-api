package dev.kertz.weather;

public enum Descriptor{
    MI(" superficial"),
    BC(" bancos"),
    PR(" parcial"),
    DR(" ventiscas bajas"),
    BL(" ventiscas altas"),
    SH(" con chaparrones"),
    TS(" con tormentas"),
    FZ(" engelantes");

    private final String description;

    Descriptor(String description){
        this.description = description;
    }

    public String description(){
        return description;
    }
}
