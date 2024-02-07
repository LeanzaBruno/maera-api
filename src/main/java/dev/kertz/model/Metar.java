package dev.kertz.model;

import dev.kertz.core.MetarDecoder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Metar extends AviationBriefing {

    private WindInformation wind;
    private String pressure;
    private TemperatureInfo temperature;
    private String visibility;
    private String remarks;
    private List<String> clouds;
    private boolean isCavok;
    private boolean noSignificantClouds;
    private boolean noSignificalChanges;

    public Metar(String raw){
        super(raw);
        MetarDecoder.decode(this);
    }

}

