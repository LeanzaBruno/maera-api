package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class AirportDecoder extends Decoder {

    //TODO AirportRepository

    AirportDecoder(){
        super("S[A-Z]{3}");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection){
        Matcher matcher = super.getMatcher(section);
        if( matcher.find() )
            return Optional.of(new Decodification(List.of(section), "Reporte referido a " + matcher.group() + "."));
        return Optional.empty();
    }
}
