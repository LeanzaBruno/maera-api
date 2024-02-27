package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class VisibilityDecoder extends SingleSectionDecoder {
    public VisibilityDecoder(){
        super("(?<!Q)\\d{4}(?!.)");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);
        if (matcher.find()) {
            int visibility = parseInt( matcher.group() );
            setDecoding( new Decoding(List.of(rawSections[0]), "Visibilidad de " + (visibility == 9999 ? "más de 10 km." : visibility + " m.")) );
            return true;
        }
        return false;
    }
}
