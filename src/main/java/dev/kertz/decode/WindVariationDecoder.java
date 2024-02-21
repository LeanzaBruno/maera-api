package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class WindVariationDecoder extends Decoder {

    WindVariationDecoder(){
        super("(?<from>\\d{3})V(?<to>\\d{3})");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if(matcher.find()){
            String from = matcher.group("from");
            String to = matcher.group("to");
            return Optional.of( new Decodification(List.of(section), "El viento varía desde los " + from + "° hasta los " + to + "°."));
        }
        return Optional.empty();
    }
}