package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

public class EndOfReportDecoder extends SingleSectionDecoder implements NotReusable{

    @Getter
    boolean used = false;

    public EndOfReportDecoder(){
        super("=");
    }

    @Override
    public boolean decode(String[] sections) {
        Matcher matcher = super.getMatcher(sections[0]);
        if(matcher.find()){
            setDecoding(new Decoding(List.of(sections[0]), "Fin del reporte"));
            return true;
        }
        return false;
    }

    public void markAsUsed(){
        used = true;
    }

    public void markAsUnused(){
        used = false;
    }

    public boolean wasUsed(){
        return used;
    }
}
