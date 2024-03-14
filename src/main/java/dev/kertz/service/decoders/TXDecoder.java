package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class TXDecoder extends SingleDecoder {

    public TXDecoder(){
        super("TX(?<temp>M?\\d{2})/(?<tempDate>\\d{2})(?<tempTime>\\d{2})Z");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            String temp = parseInt(matcher.group("temp").replace('M', '-')) + "°C";
            int tempDate = parseInt(matcher.group("tempDate"));
            String tempTime = matcher.group("tempTime") + ":00 UTC";
            String decodification =  "Se espera una temperatura máxima de " + temp + " el día " + tempDate + " a las " + tempTime + ".";
            return Optional.of(new Decoding(decodification, section));
        }
        return Optional.empty();
    }
}
