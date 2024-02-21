package dev.kertz.dto;

import dev.kertz.decode.*;
import dev.kertz.model.Taf;
import java.util.ArrayList;
import java.util.List;

public class TafMapper {

    public static ReportDTO toDTO(Taf taf){
        final String raw = taf.getRaw();

        List<Decodification> decodifications = new ArrayList<>();
        String [] list = raw.split(" ");
        for(int index = 0 ; index < list.length ; ++index){
            for(var decoder : TafDecoders.list){
                String section = list[index];
                String nextSection = index+1 < list.length ? list[index+1] : null;
                var wrapper = new Object(){ boolean decoded = false; int extraSteps = 0; };
                decoder.decode(section, nextSection).ifPresent( decodification -> {
                    decodifications.add(decodification);
                    wrapper.decoded = true;
                    wrapper.extraSteps = decodification.getDecodedSections() - 1;
                } );

                if(wrapper.decoded){
                    index += wrapper.extraSteps;
                    break;
                }
            }
        }
        return new ReportDTO(raw, decodifications);
    }
}
