package dev.kertz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum WeatherQualifier{
    LIGHT("-"),
    MODERATE(null),
    HEAVY( "+"),
    VECINITY("VC");

    private final String symbol;
    private WeatherQualifier(String symbol){
        this.symbol = symbol;
    }

}
