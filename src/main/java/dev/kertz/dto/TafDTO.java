package dev.kertz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TafDTO {
    private String raw;
    private String airportCode;
    private String publicationDate;
    private String publicationTime;
    private String validFromDate;
    private String validFromTime;
    private String validToDate;
    private String validToTime;
    private String windDirection;
    private String windIntensity;
    private String windGusts;
    private String currentConditions;
    private List<String> currentConditionsArr;
    private String maxTemperature;
    private String maxTemperatureDate;
    private String maxTemperatureTime;
    private String minTemperature;
    private String minTemperatureDate;
    private String minTemperatureTime;
    private String expectedConditions;
}
