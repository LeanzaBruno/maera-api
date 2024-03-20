package dev.kertz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Decoding {
    private final LinkedList<String> sections;

    private final String decodification;

    public Decoding(String decodification, String ... sections){
        this.decodification = decodification;
        this.sections = new LinkedList<>(Arrays.stream(sections).toList());
    }

    public String getDecoding(){
        return decodification;
    }

    public String getSection(){
        StringBuilder builder = new StringBuilder();
        for(int index = 0 ; index < sections.size() ; ++index)
            builder.append(sections.get(index)).append(index + 1 < sections.size() ? ' ' : "");
        return builder.toString();
    }

    @JsonIgnore
    public int getDecodedSections(){
        return sections.size();
    }

    @Override
    public String toString(){
        return "[section: \"" + getSection() + "\", decodification: \"" + decodification + "\"]";
    }
}