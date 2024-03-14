package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class TemperatureDecoder extends SingleDecoder {

    public TemperatureDecoder(){
        super("^(?<temperature>M?\\d{2})/(?<dewPoint>M?\\d{2})$");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if( matcher.find() ){
            int temperature = parseInt(matcher.group("temperature").replace('M', '-'));
            int dewPoint = parseInt(matcher.group("dewPoint").replace('M', '-'));
            String decodification =  "La temperatura es de " + temperature + "°C, y el punto de rocío es de " + dewPoint + "°C.";
            return Optional.of(new Decoding(decodification, section));
        }
        return Optional.empty();
    }
}