package dev.kertz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetarDTO {
    private String raw;
    private List<Section> sections;

    MetarDTO(String raw){
        this.raw = raw;
    }

}

