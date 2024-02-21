package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PressureDecoder extends Decoder {

    PressureDecoder(){
        super("(?<=Q)\\d{4}");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);
        if( matcher.find() )
            return Optional.of(new Decodification(List.of(section), "La presión atmosférica es de " + parseInt(matcher.group()) + " hPa."));
        return Optional.empty();
    }
}