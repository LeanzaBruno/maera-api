package dev.kertz.decode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class that decodes sections of a report
 */
public abstract class Decoder {

    /**
     * The pattern used to
     */
    private final Pattern pattern;

    Decoder(String regex){
        pattern = Pattern.compile(regex);
    }

    /**
     * This method decodes the section of the report in normal words
     * @return the decoded section, or null if there wasn't a match
     */
    public abstract String decode(String section);

    protected final Matcher getMatcher(String section){
        return pattern.matcher(section);
    }

}
