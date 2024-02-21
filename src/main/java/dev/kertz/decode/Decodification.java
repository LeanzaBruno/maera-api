package dev.kertz.decode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({"section", "decodification"})
public class Decodification{
    private final List<String> sections;

    private final String decodification;

    Decodification(List<String> sections, String decodification){
        this.sections = sections;
        this.decodification = decodification;
    }

    public String getSection(){
        StringBuilder builder = new StringBuilder();
        for(int index = 0 ; index < sections.size() ; ++index)
            builder.append(sections.get(index)).append(index + 1 < sections.size() ? ' ' : "");
        return builder.toString();
    }

    public String getDecodification(){
        return decodification;
    }

    @JsonIgnore
    public int getDecodedSections(){
        return sections.size();
    }

    @Override
    public String toString(){
        return "section: " + getSection() + ",\ndecodification: " + decodification;
    }
}