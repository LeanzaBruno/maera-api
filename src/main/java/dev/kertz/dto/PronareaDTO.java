package dev.kertz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PronareaDTO {
    private String raw;
    private String firIdentifier;
    private String validTimeFrom;
    private String validTimeTo;
    private String mapTime;
    private String sigFenom;
    private String jetStream;
    private String turbulence;
    private String freezing;
    private String isotherm;
    private String tropopause;
    private String windAndTemp;
    private String forecast;
}
