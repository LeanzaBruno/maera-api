package dev.kertz.decode;

import dev.kertz.model.Airport;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.regex.Matcher;

public class AirportDecoder extends SingleSectionDecoder implements NotReusable {

    @Setter
    private Airport airport;

    @Getter
    private boolean used = false;

    public AirportDecoder(){
        super("S[A-Z]{3}");
    }

    @Override
    public boolean decode(String[] sections){
        Matcher matcher = super.getMatcher(sections[0]);
        if( matcher.find() ){
                setDecoding(new Decoding(List.of(sections[0]), "Reporte referido al " + airport.getName() + "."));
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
