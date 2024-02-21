package dev.kertz.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CloudCoverType {
    FEW("Nubes escasas (de 1/8 a 2/8)"),
    SCT("Nubes dispersas (de 3/8 a 4/8)"),
    BKN("Nubes fragmentadas (de 5/8 a 7/8)"),
    OVC("Cielo cubierto (8/8)");

    private final String meaning;

    private CloudCoverType(String meaning){
        this.meaning = meaning;
    }

}
