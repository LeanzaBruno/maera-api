package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

@Getter
public class WindVariationDecoder extends SingleSectionDecoder implements NotReusable {

    private boolean used = false;

    public WindVariationDecoder(){
        super("(?<from>\\d{3})V(?<to>\\d{3})");
    }

    @Override
    public boolean decode(String[] sections) {
        Matcher matcher = super.getMatcher(sections[0]);

        if(matcher.find()){
            String from = matcher.group("from");
            String to = matcher.group("to");
            setDecoding( new Decoding(List.of(sections[0]), "El viento varía desde los " + from + "° hasta los " + to + "°."));
            return true;
        }
        return false;
    }

    public void markAsUsed(){
        used = true;
    }

    public boolean wasUsed(){
        return used;
    }

    public void markAsUnused(){
        used = false;
    }
}