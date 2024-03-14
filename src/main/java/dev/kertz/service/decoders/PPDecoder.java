package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class PPDecoder extends SingleDecoder {

    public PPDecoder(){
        super("(?<=PP)(\\d{3}|\\/\\/\\/)");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            String pp = matcher.group();
            return Optional.of(new Decoding(pp.equals("///") ? "El sensor se encuentra fuera de servicio." : "La precipitación en la última hora fue de " + parseInt(pp) + " mm.", section));
        }
        return Optional.empty();
    }
}
