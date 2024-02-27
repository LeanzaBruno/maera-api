package dev.kertz.decode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SingleSectionDecoder extends Decoder {

    private final Pattern pattern;

    SingleSectionDecoder(String regex){
        this.pattern = Pattern.compile(regex);
    }

    protected Matcher getMatcher(String section) {
        return pattern.matcher(section);
    }
}
