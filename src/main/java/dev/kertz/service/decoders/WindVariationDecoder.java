package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class WindVariationDecoder extends SingleDecoder {


    public WindVariationDecoder(){
        super("(?<from>\\d{3})V(?<to>\\d{3})");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if(matcher.find()){
            String from = matcher.group("from");
            String to = matcher.group("to");
            return Optional.of( new Decoding("El viento varía desde los " + from + "° hasta los " + to + "°.", section));
        }
        return Optional.empty();
    }
}