package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class PublicationDecoder extends Decoder {

    PublicationDecoder(){
        super("(?<date>\\d{2})(?<hours>\\d{2})(?<minutes>\\d{2})Z");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            String dayofMonth = matcher.group("date");
            String hours = matcher.group("hours");
            String minutes = matcher.group("minutes");
            return Optional.of(new Decodification(List.of(section), "El reporte pertenece a la hora " + hours + ":" + minutes + " UTC del día " + dayofMonth + "."));
        }
        return Optional.empty();
    }
}
