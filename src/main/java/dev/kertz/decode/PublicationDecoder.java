package dev.kertz.decode;

import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class PublicationDecoder extends Decoder{

    PublicationDecoder(){
        super("(?<date>\\d{2})(?<hours>\\d{2})(?<minutes>\\d{2})Z");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            String dayofMonth = matcher.group("date");
            String hours = matcher.group("hours");
            String minutes = matcher.group("minutes");
            return "El reporte pertenece a la hora " + hours + ":" + minutes + " UTC del día " + dayofMonth + ".";
        }
        else return null;
    }
}
