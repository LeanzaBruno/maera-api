package dev.kertz.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MetarDTO {
    private String raw;
    private String airportCode;
    private String publicationDate;
    private String publicationTime;
    private String windDirection;
    private String windIntensity;
    private String windGusts;
    private String windVariationFrom;
    private String windVariationTo;
    private String temperature;
    private String dewPoint;
    private String QNH;
    private String visibility;
    private String remarks;
    private List<String> clouds;
    private boolean CAVOK;
    private boolean NSC;
    private boolean NOSIG;
}
