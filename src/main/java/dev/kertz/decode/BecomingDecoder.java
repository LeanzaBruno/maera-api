package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class BecomingDecoder extends Decoder {
    private static final Pattern BECOMING_PATTERN = Pattern.compile("BECMG");
    private static final Pattern PERIOD_PATTERN = Pattern.compile("(?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");

    @Override
    public boolean decode(String[] rawSections) {
        if( rawSections.length < 2)
            return false;

        // TODO hacerlo escalonado
        Matcher matcher = BECOMING_PATTERN.matcher(rawSections[0]);
        if (matcher.find()) {
            Matcher periodMatcher = PERIOD_PATTERN.matcher(rawSections[1]);
            if(periodMatcher.find())
                setDecoding( new Decoding(List.of(rawSections[0], rawSections[1]), getDecodingString(periodMatcher)));
            else
                setDecoding( new Decoding(List.of(rawSections[0]), "Cambio de condiciones meteorológicas dentro de las 2 horas de emitido el reporte."));
            return true;
        }
        return false;
    }

    private String getDecodingString(Matcher matcher){
        int fmDate = parseInt( matcher.group("fmDate"));
        String fmTime = matcher.group("fmTime");
        int toDate = parseInt( matcher.group("toDate") );
        String toTime = matcher.group("toTime");
        return "Cambio de las condiciones entre las " + fmTime + ":00 UTC del día " + fmDate + ", y las " + toTime + ":00 UTC del día " + toDate + ".";
    }
}