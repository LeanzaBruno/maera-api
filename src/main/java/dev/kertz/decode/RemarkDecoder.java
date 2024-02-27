package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

@Getter
public class RemarkDecoder extends SingleSectionDecoder implements NotReusable {

    private boolean used = false;

    public RemarkDecoder(){
        super("RMK");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);

        if(matcher.find()){
            setDecoding(new Decoding(List.of(rawSections[0]), "El reporte tiene observaciones adicionales."));
            return true;
        }
        return false;
    }

    public void markAsUsed(){
        used = true;
    }

    public boolean wasUsed(){
        return used;
    }

    public void markAsUnused(){
        used = false;
    }
}