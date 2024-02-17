package dev.kertz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum CloudCoverType {
    FEW("1/8 - 2/8"),
    SCT("3/8 - 4/8"),
    BKN("5/8 - 7/8"),
    OVC("8/8"),
    NSC("0/8");

    private final String range;

    private CloudCoverType(String range){
        this.range = range;
    }

    public String getSymbol(){
        return name();
    }
}
