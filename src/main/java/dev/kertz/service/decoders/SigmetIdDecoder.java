package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class SigmetIdDecoder extends SingleDecoder {

    public SigmetIdDecoder(){
        super("\b\\w?\\d\b");
    }

    @Override
    public Optional<Decoding> decode(String section){
        Matcher matcher = super.getMatcher(section);
        if(matcher.find())
            return Optional.of( new Decoding("El identificador del SIGMET", section));
        return Optional.empty();
    }

}
