package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;

public class CealingAndVisibilityOKDecoder extends SingleSectionDecoder {

    public CealingAndVisibilityOKDecoder(){
        super("CAVOK");
    }

    @Override
    public boolean decode(String[] sections) {
        Matcher matcher =  super.getMatcher(sections[0]);
        if( matcher.find() ){
            setDecoding(new Decoding(
                    List.of(sections[0]),
                    "Visibilidad mayor a 10 km, sin nubes por debajo de 5000 ft sin CB ni TCU, y no hay fenómenos meteorólogicos significativos."));
            return true;
        }
        return false;
    }
}