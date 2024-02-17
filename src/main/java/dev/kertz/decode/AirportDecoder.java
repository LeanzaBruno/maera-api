package dev.kertz.decode;

import java.util.regex.Matcher;

public class AirportDecoder extends Decoder {

    AirportDecoder(){
        super("S[A-Z]{3}");
    }


    @Override
    public String decode(String section){
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            return "Reporte referido al aeropuerto de " + matcher.group() + ".";
        }
        return null;
    }

}
