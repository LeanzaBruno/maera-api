package dev.kertz.enums;

import lombok.Getter;

public class Reports{

    @Getter
    public enum Type {
        METAR("Informe meteorológico ordinario del aeródromo"),
        SPECI("Informe meteorológico especial del aeródromo"),
        TAF("Pronóstico de aeródromo");

        private final String meaning;

        Type(String meaning){
            this.meaning = meaning;
        }

        public String getName(){
            return name().replace('_', ' ');
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
