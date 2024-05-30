package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import lombok.Getter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class AffectedAreaDecoder extends MultiDecoder{

    private final Pattern WITHIN_PATTERN = Pattern.compile("WI");
    private final Pattern COORD_PATTERN = Pattern.compile("S(?<southDeg>\\d{2})(?<southMin>\\d{2}) W(?<westDeg>\\d{3})(?<westMin>\\d{2})");

    @Getter
    private final List<String> coordinates = new LinkedList<>();


    @Override
    public Optional<Decoding> decode(String ... sections){
        Matcher matcher = WITHIN_PATTERN.matcher(sections[0]);
        if(matcher.find()){

            int i = 1;
            matcher = COORD_PATTERN.matcher(sections[i]);
            while(matcher.find()){
                String southDeg = matcher.group("southDeg") + "°";
                String southMin = matcher.group("southMin") + "'";
                String westDeg = matcher.group("westDeg") + "°";
                String westMin = matcher.group("westMin") + "'";
                coordinates.add('S' + southDeg + southMin + " W" + westDeg + westMin);
            }

            return Optional.of(new Decoding("El área afectada por el SIGMET", Arrays.copyOfRange(sections, 0, i)));
       }
       return Optional.empty();
    }
}
