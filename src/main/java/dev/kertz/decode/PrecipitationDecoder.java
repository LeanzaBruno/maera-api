package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PrecipitationDecoder extends Decoder {

    PrecipitationDecoder(){
        super("(?<=PP)(\\d{3}|\\/\\/\\/)");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            String pp = matcher.group();
            return Optional.of(new Decodification(List.of(section), pp.equals("///") ? "El sensor se encuentra fuera de servicio." : "La precipitación en la última hora fue de " + parseInt(pp) + " mm."));
        }
        return Optional.empty();
    }
}
