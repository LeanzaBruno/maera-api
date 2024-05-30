package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class VisibilityDecoder extends SingleDecoder {

    public VisibilityDecoder(){
        super("^[0-9]{4}$");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if (matcher.find()) {
            int visibility = parseInt( matcher.group() );
            return Optional.of( new Decoding("Visibilidad de " + (visibility == 9999 ? "más de 10 km." : visibility + " m."), section));
        }
        return Optional.empty();
    }
}
