package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;

@Service
public class PublicationDecoder extends SingleDecoder {

    public PublicationDecoder(){
        super("(?<date>\\d{2})(?<hours>\\d{2})(?<minutes>\\d{2})Z");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            String dayofMonth = matcher.group("date");
            int hours = parseInt( matcher.group("hours") );
            int minutes = parseInt( matcher.group("minutes"));
            LocalTime utc = LocalTime.of(hours, minutes);
            LocalTime local = LocalTime.of(hours-3 , minutes);
            return Optional.of(new Decoding("El reporte pertenece a la hora " + utc + " UTC (" + local + " UTC-3) del día " + dayofMonth + ".", section));
        }
        return Optional.empty();
    }
}
