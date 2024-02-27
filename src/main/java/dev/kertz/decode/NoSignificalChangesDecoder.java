package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;

public class NoSignificalChangesDecoder extends SingleSectionDecoder {

    public NoSignificalChangesDecoder(){
        super("NOSIG");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);
        if(matcher.find()){
            setDecoding(new Decoding(List.of(rawSections[0]), "No se esperan cambios significativos en las próximas 2 horas."));
            return true;
        }
        return false;
    }
}