package dev.kertz.decode;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Decoder {

    private final Pattern pattern;
    protected int decodedSectionsCount = 0;

    Decoder(String regex){
        pattern = Pattern.compile(regex);
    }
    //TODO resolver
    protected final Matcher getMatcher(String section){
        return pattern.matcher(section);
    }
    public abstract Optional<Decodification> decode(String section, String nextSection);
    public final int getDecodedSections(){
        return decodedSectionsCount;
    }
}
