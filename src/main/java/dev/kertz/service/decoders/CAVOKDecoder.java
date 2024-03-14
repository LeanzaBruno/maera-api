package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class CAVOKDecoder extends SingleDecoder {

    public CAVOKDecoder(){
        super("CAVOK");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher =  super.getMatcher(section);
        if( matcher.find() )
            return Optional.of( new Decoding("Visibilidad mayor a 10 km, sin nubes por debajo de 5000 ft, sin CB ni TCU, y no hay fenómenos meteorólogicos significativos.", section));
        return Optional.empty();
    }
}