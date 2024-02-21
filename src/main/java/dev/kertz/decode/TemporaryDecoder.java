package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class TemporaryDecoder {

    public TemporaryDecoder(){
        super("TEMPO");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            Matcher nextSectionMatcher = Pattern.compile("(?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})").matcher(nextSection);

            if (nextSectionMatcher.find()) {
                int fmDate = parseInt( nextSectionMatcher.group("fmDate"));
                String fmTime = nextSectionMatcher.group("fmTime");
                int toDate = parseInt( nextSectionMatcher.group("toDate") );
                String toTime = nextSectionMatcher.group("toTime");

                return Optional.of( new Decodification(
                        List.of(section, nextSection),
                        "Cambio de las condiciones entre las " + fmTime + ":00 UTC del día " + fmDate + ", hasta las " + toTime + ":00 UTC del día " + toDate + "."));
            }
            return Optional.of(new Decodification(List.of(section), "Indicación de que habrá un cambio de condiciones meteorológicas dentro de las 2 horas de emitido el reporte."));
        }
        return Optional.empty();
    }
}
