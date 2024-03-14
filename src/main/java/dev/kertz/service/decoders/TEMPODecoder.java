package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

@Service
public class TEMPODecoder extends MultiDecoder {

    private final static Pattern PROB_TEMPO_ARREGMENT = Pattern.compile("PROB(?<probability>\\d{2}) TEMPO (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    private final static Pattern TEMPO_ARREGMENT = Pattern.compile("TEMPO (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    private final static Pattern PROB_ARREGMENT = Pattern.compile("PROB(?<probability>\\d{2}) (?<fmDate>\\d{2})(?<fmTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");

    @Override
    public Optional<Decoding> decode(String[] sections) {
        if( sections.length < 3 )
            return Optional.empty();

        String target = sections[0] + ' ' + sections[1] + ' ' + sections[2];
        String decoding = null;
        Matcher matcher = PROB_TEMPO_ARREGMENT.matcher(target);
        if( matcher.find()){
            int probability = parseInt( matcher.group("probability") );
            decoding = "Cambio temporal de las condiciones que puede ocurrir " + makePeriodString(matcher) + ", con una probabilidad del " + probability + "%.";
            return Optional.of(new Decoding(decoding, sections[0], sections[1], sections[2]));
        }

        matcher = TEMPO_ARREGMENT.matcher(target);
        if(matcher.find())
            decoding = "Cambio temporal de las condiciones que puede ocurrir " + makePeriodString(matcher) + ".";
        else{
            matcher = PROB_ARREGMENT.matcher(target);
            if(matcher.find())
                decoding = "Probabilidad del "+ parseInt(matcher.group("probability")) + "% de que ocurra un cambio en las condiciones " + makePeriodString(matcher) + '.';
        }

        if(decoding != null)
            return Optional.of(new Decoding(decoding, sections[0], sections[1]));
        return Optional.empty();
    }

    private String makePeriodString(Matcher matcher){
        int fmDate = parseInt( matcher.group("fmDate"));
        String fmTime = matcher.group("fmTime");
        int toDate = parseInt( matcher.group("toDate") );
        String toTime = matcher.group("toTime");
        return "entre las "+ fmTime + ":00 UTC del día " + fmDate + " y las " + toTime + ":00 UTC del día " + toDate;
    }
}
