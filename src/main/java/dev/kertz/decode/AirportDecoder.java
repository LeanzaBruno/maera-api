package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

public class AirportDecoder extends SingleSectionDecoder implements NotReusable {

    //TODO AirportRepository

    @Getter
    private boolean used = false;

    public AirportDecoder(){
        super("S[A-Z]{3}");
    }

    @Override
    public boolean decode(String[] sections){
        Matcher matcher = super.getMatcher(sections[0]);
        if( matcher.find() ){
            setDecoding(new Decoding(List.of(sections[0]), "Reporte referido a " + matcher.group() + "."));
            return true;
        }
        return false;
    }

    public void markAsUsed(){
        used = true;
    }

    public boolean wasUsed(){
        return used;
    }

    public void markAsUnused(){
        used = false;
    }
}
