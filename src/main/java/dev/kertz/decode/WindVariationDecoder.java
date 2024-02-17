package dev.kertz.decode;

import java.util.regex.Matcher;

public class WindVariationDecoder extends Decoder{

    WindVariationDecoder(){
        super("(?<from>\\d{3})V(?<to>\\d{3})");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if(matcher.find()){
            String from = matcher.group("from");
            String to = matcher.group("to");

            return "El viento varía desde los " + from + "° hasta los " + to + "°.";
        }
        return null;
    }
}
