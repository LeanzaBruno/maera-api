package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class TemporaryDecoder extends Decoder {

    private final static Pattern PROB_TEMPO_ARREGMENT = Pattern.compile("PROB(?<probability>\\d{2}) TEMPO (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    private final static Pattern TEMPO_ARREGMENT = Pattern.compile("TEMPO (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    private final static Pattern PROB_ARREGMENT = Pattern.compile("PROB(?<probability>\\d{2}) (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");

    @Override
    public boolean decode(String[] rawSections) {
        if( rawSections.length < 3 )
            return false;

        String target = rawSections[0] + ' ' + rawSections[1] + ' ' + rawSections[2];
        String decoding = null;
        Matcher matcher = PROB_TEMPO_ARREGMENT.matcher(target);
        if( matcher.find()){
            int probability = parseInt( matcher.group("probability") );
            decoding = "Cambio temporal de las condiciones que puede ocurrir " + makePeriodString(matcher) + ", con una probabilidad del " + probability + "%.";
            setDecoding(new Decoding(List.of(rawSections[0], rawSections[1], rawSections[2]), decoding));
            return true;
        }

        matcher = TEMPO_ARREGMENT.matcher(target);
        if(matcher.find())
            decoding = "Cambio temporal de las condiciones que puede ocurrir " + makePeriodString(matcher) + ".";
        else{
            matcher = PROB_ARREGMENT.matcher(target);
            if(matcher.find())
                decoding = "Probabilidad del "+ parseInt(matcher.group("probability")) + "% de que ocurra un cambio en las condiciones " + makePeriodString(matcher) + '.';
        }

        if(decoding != null){
            setDecoding(new Decoding(List.of(rawSections[0], rawSections[1]), decoding));
            return true;
        }

        return false;
    }

    private String makePeriodString(Matcher matcher){
        int fmDate = parseInt( matcher.group("fmDate"));
        String fmTime = matcher.group("fmTime");
        int toDate = parseInt( matcher.group("toDate") );
        String toTime = matcher.group("toTime");
        return "entre las "+ fmTime + ":00 UTC del día " + fmDate + " y las " + toTime + ":00 UTC del día " + toDate;
    }
}
