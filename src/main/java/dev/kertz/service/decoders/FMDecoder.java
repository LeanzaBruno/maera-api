package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class FMDecoder extends SingleDecoder {

    public FMDecoder(){
        super("FM(?<date>\\d{2})(?<hh>\\d{2})(?<mm>\\d{2})");
    }

    @Override
    public Optional<Decoding> decode(String section){
        Matcher matcher = getMatcher(section);

        if(matcher.find()){
            int date = parseInt( matcher.group("date") );
            String time = matcher.group("hh") + ":" + matcher.group("mm") + " UTC";
            return Optional.of(new Decoding("A partir de las " + time + " del día " + date +", se espera un cambio permanente de las condiciones meteorológicas.", section));
        }
        return Optional.empty();
    }
}
