package dev.kertz.decode;

import dev.kertz.enums.WeatherDescriptor;
import dev.kertz.enums.WeatherPhenomena;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class WeatherPhenomenaDecoder extends Decoder {

    WeatherPhenomenaDecoder(){
        super("(?<qualifier>[+-]|VC)?(?<descriptor>\\w{2})?(?<phenomena>DZ|RA|SN|PL|GR|GS|BR|FG|FU|VA|DU|SA|HZ|PO|SQ|FC|SS|DS)");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            var wfWrapper = new Object(){ String phenomena; };
            Stream.of( WeatherPhenomena.values() )
                    .filter( wf -> wf.name().equals(matcher.group("phenomena")))
                    .findFirst()
                    .ifPresent( wf -> wfWrapper.phenomena = wf.getMeaning() );


            var wdWrapper = new Object(){ String descriptor; };
            Stream.of(WeatherDescriptor.values() )
                    .filter( wd -> wd.name().equals(matcher.group("descriptor")) )
                    .findFirst()
                    .ifPresent( wd -> wdWrapper.descriptor = wd.getMeaning() );

            String qualifier = switch( matcher.group("qualifier") ){
                case "-" -> "con intensidad leve.";
                case "+" -> "con intensidad fuerte.";
                case "VC" -> "en las proximidades.";
                case null -> "con intensidad moderada.";
                default -> null;
            };

            return Optional.of( new Decodification(List.of(section), wfWrapper.phenomena + (wdWrapper.descriptor != null ? wdWrapper.descriptor : "") + " " + qualifier));
        }
        return Optional.empty();
    }
}