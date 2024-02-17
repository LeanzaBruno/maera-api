package dev.kertz.dto;

import dev.kertz.decode.MetarDecoders;
import dev.kertz.model.Metar;
import java.util.ArrayList;
import java.util.List;

public class MetarMapper {

    public static MetarDTO toDTO(Metar metar){
        final String raw = metar.getRaw();
        MetarDTO dto = new MetarDTO(raw);

        List<Section> sections = new ArrayList<>();
        String [] splittedReport = raw.split(" ");
        for(var section : splittedReport ){
            for(var decoder : MetarDecoders.list){
                /*
                TODO Para lograr esto tengo q hacer que los metares guarden su aeropuerto correspondiente
                De ese modo, al decodificar el aeropuerto se puede dar mas informacion, como el nombre o la ciudad
                String decodification;
                if(decoder instanceof AirportDecoder)
                    decodification = decoder.decode(section, metar.getAirport());
                else
                    decodification = decoder.decode(section);
                */
                String decodification = decoder.decode(section);
                if(decodification != null) {
                    sections.add(new Section(section, decodification));
                    break;
                }
            }
        }

        dto.setSections(sections);
        return dto;
    }

}
