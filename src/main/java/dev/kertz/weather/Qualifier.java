package dev.kertz.weather;

public enum Qualifier{
    LIGHT("-", " con intensidad leve."),
    MODERATE("", " con intensidad moderada."),
    HEAVY("+", " con fuerte intensidad."),
    RECENT("RE", " recientes."),
    VECINITY("VC", " en las proximidades.");

    private final String symbol;
    private final String description;

    Qualifier(String symbol, String description){
        this.symbol = symbol;
        this.description = description;
    }

    public String symbol(){
        return symbol;
    }

    public String description(){
        return description;
    }
}
