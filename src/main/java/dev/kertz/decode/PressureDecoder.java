package dev.kertz.decode;

import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;

public class PressureDecoder extends Decoder {

    PressureDecoder(){
        super("(?<=Q)\\d{4}");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if( matcher.find() )
            return "La presión atmosférica es de " + parseInt(matcher.group()) + " hPa.";
        return null;
    }
}
