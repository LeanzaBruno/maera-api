package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

@Getter
public class PublicationDecoder extends SingleSectionDecoder implements NotReusable {
    private boolean used = false;

    public PublicationDecoder(){
        super("(?<date>\\d{2})(?<hours>\\d{2})(?<minutes>\\d{2})Z");
    }

    @Override
    public boolean decode(String[] sections) {
        Matcher matcher = super.getMatcher(sections[0]);

        if( matcher.find() ){
            String dayofMonth = matcher.group("date");
            String hours = matcher.group("hours");
            String minutes = matcher.group("minutes");
            setDecoding(new Decoding(List.of(sections[0]), "El reporte pertenece a la hora " + hours + ":" + minutes + " UTC del día " + dayofMonth + "."));
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
