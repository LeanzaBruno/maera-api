package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

@Service
public class BECMGDecoder extends MultiDecoder {
    private static final Pattern BECOMING_PATTERN = Pattern.compile("BECMG");
    private static final Pattern PERIOD_PATTERN = Pattern.compile("(?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");

    @Override
    public Optional<Decoding> decode(String [] rawSections) {
        if( rawSections.length < 2)
            return Optional.empty();

        Matcher matcher = BECOMING_PATTERN.matcher(rawSections[0]);
        if (matcher.find()) {
            Matcher periodMatcher = PERIOD_PATTERN.matcher(rawSections[1]);
            if(periodMatcher.find())
                return Optional.of( new Decoding(getDecodingString(periodMatcher), rawSections[0], rawSections[1]));
            else
                return Optional.of( new Decoding(rawSections[0], "Cambio de condiciones meteorológicas dentro de las 2 horas de emitido el reporte."));
        }
        return Optional.empty();
    }

    private String getDecodingString(Matcher matcher){
        int fmDate = parseInt( matcher.group("fmDate"));
        String fmTime = matcher.group("fmTime");
        int toDate = parseInt( matcher.group("toDate") );
        String toTime = matcher.group("toTime");
        return "Cambio de las condiciones entre las " + fmTime + ":00 UTC del día " + fmDate + " y las " + toTime + ":00 UTC del día " + toDate + ".";
    }
}