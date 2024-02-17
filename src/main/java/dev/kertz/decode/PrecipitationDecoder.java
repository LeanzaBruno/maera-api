package dev.kertz.decode;

import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PrecipitationDecoder extends Decoder{

    PrecipitationDecoder(){
        super("(?<=PP)(\\d{3}|\\/\\/\\/)");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if(matcher.find()){
            String pp = matcher.group();
            return (pp.equals("///") ? "El sensor se encuentra fuera de servicio." : "La precipitación en la última hora fue de " + parseInt(pp) + " mm.");
        }
        return null;
    }
}
