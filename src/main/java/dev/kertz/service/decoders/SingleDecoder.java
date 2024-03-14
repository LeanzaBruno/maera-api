package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SingleDecoder {
    protected final Pattern pattern;

    public SingleDecoder(String regex){
        pattern = Pattern.compile(regex);
    }

    protected final Matcher getMatcher(String section) {
        return pattern.matcher(section);
    }

    abstract public Optional<Decoding> decode(String section);
}
