package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

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
            String hours = matcher.group("hours");
            String minutes = matcher.group("minutes");
            return Optional.of(new Decoding("El reporte pertenece a la hora " + hours + ":" + minutes + " UTC del día " + dayofMonth + ".", section));
        }
        return Optional.empty();
    }
}
