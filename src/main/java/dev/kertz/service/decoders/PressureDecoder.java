package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class PressureDecoder extends SingleDecoder {

    public PressureDecoder(){
        super("(?<=Q)\\d{4}");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if( matcher.find() ) {
            return Optional.of(new Decoding("La presión atmosférica es de " + parseInt(matcher.group()) + " hPa.", section));
        }
        return Optional.empty();
    }
}