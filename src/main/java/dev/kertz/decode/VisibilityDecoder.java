package dev.kertz.decode;

import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class VisibilityDecoder extends Decoder{

    VisibilityDecoder(){
        super("(?<!Q)\\d{4}(?!.)");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            int visibility = parseInt( matcher.group() );
            return "La visibilidad es de " + (visibility == 9999 ? "más de 10 km." : visibility + " metros.");
        }
        
        return null;
    }
}
