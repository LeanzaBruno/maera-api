package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class NOSIGDecoder extends SingleDecoder {

    public NOSIGDecoder(){
        super("NOSIG");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            return Optional.of(new Decoding( "No se esperan cambios significativos en las próximas 2 horas.", section));
        }
        return Optional.empty();
    }
}