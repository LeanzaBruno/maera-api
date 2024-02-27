package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class TemporaryDecoder extends Decoder {

    //private final static Pattern PATTERN = Pattern.compile("(PROB(?<probability>\\d{2}) )?TEMPO (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    private static final Pattern PROBABILITY_PATTERN = Pattern.compile("PROB(?<prob>\\d{2})");
    private static final Pattern TEMPO_PATTERN = Pattern.compile("TEMPO");
    private static final Pattern PERIOD_PATTERN = Pattern.compile("(?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");

    @Override
    public boolean decode(String[] rawSections) {
        if( rawSections.length < 3 )
            return false;

        Matcher probMatcher = PROBABILITY_PATTERN.matcher(rawSections[0]);
        if( probMatcher.find()){
            int probability = parseInt( probMatcher.group("prob") );

            Matcher tempoMatcher = TEMPO_PATTERN.matcher(rawSections[1]);
            if( tempoMatcher.find()){
                Matcher periodMatcher = PERIOD_PATTERN.matcher(rawSections[2]);
                if( periodMatcher.find() ){
                    int fmDate = parseInt( periodMatcher.group("fmDate"));
                    String fmTime = periodMatcher.group("fmTime");
                    int toDate = parseInt( periodMatcher.group("toDate") );
                    String toTime = periodMatcher.group("toTime");

                    setDecoding(
                            new Decoding(
                                    List.of(rawSections[0], rawSections[1], rawSections[2]),
                                    "Cambio temporal de las condiciones que puede ocurrir entre las " + fmTime + ":00 UTC del día " + fmDate + " hasta las " + toTime + ":00 UTC del día " + toDate + ", con una probabilidad del " + probability + "%."
                            )
                    );
                    return true;
                }
            }
        }
        else{
            Matcher tempoMatcher = TEMPO_PATTERN.matcher(rawSections[0]);
            if( tempoMatcher.find()){
                Matcher periodMatcher = PERIOD_PATTERN.matcher(rawSections[1]);
                if(periodMatcher.find()){
                    int fmDate = parseInt( periodMatcher.group("fmDate"));
                    String fmTime = periodMatcher.group("fmTime");
                    int toDate = parseInt( periodMatcher.group("toDate") );
                    String toTime = periodMatcher.group("toTime");

                    setDecoding(new Decoding(
                            List.of(rawSections[0], rawSections[1]),
                            "Cambio temporal de las condiciones que puede ocurrir entre las " + fmTime + ":00 UTC del día " + fmDate + " hasta las " + toTime + ":00 UTC del día " + toDate + '.'));
                    return true;
                }
            }
        }
        return false;
    }
}
