package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PrecipitationDecoder extends SingleSectionDecoder {

    public PrecipitationDecoder(){
        super("(?<=PP)(\\d{3}|\\/\\/\\/)");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);
        if(matcher.find()){
            String pp = matcher.group();
            setDecoding(new Decoding(List.of(rawSections[0]), pp.equals("///") ? "El sensor se encuentra fuera de servicio." : "La precipitación en la última hora fue de " + parseInt(pp) + " mm."));
            return true;
        }
        return false;
    }
}
