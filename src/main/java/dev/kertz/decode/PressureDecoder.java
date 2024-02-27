package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PressureDecoder extends SingleSectionDecoder {

    public PressureDecoder(){
        super("(?<=Q)\\d{4}");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);
        if( matcher.find() ) {
            setDecoding(new Decoding(List.of(rawSections[0]), "La presión atmosférica es de " + parseInt(matcher.group()) + " hPa."));
            return true;
        }
        return false;
    }
}