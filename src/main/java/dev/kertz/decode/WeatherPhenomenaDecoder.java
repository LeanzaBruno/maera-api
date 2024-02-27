package dev.kertz.decode;

import dev.kertz.enums.WeatherDescriptor;
import dev.kertz.enums.WeatherPhenomena;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.stream.Stream;

// TODO mejorar
public class WeatherPhenomenaDecoder extends SingleSectionDecoder {

    public WeatherPhenomenaDecoder(){
        super("(?<qualifier>[+-]|VC)?(?<descriptor>\\w{2})?(?<phenomena>DZ|RA|SN|PL|GR|GS|BR|FG|FU|VA|DU|TS|SA|HZ|PO|SQ|FC|SS|DS)");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);

        if (matcher.find()) {
            AtomicReference<String> phenomena = new AtomicReference<>();
            Stream.of( WeatherPhenomena.values() )
                    .filter( wf -> wf.name().equals(matcher.group("phenomena")))
                    .findFirst()
                    .ifPresent( wf -> phenomena.set(wf.getMeaning()) );


            AtomicReference<String> descriptor = new AtomicReference<>();
            Stream.of(WeatherDescriptor.values() )
                    .filter( wd -> wd.name().equals(matcher.group("descriptor")) )
                    .findFirst()
                    .ifPresent( wd -> descriptor.set(wd.getMeaning()) );

            String qualifier = switch( matcher.group("qualifier") ){
                case "-" -> "con leve intensidad.";
                case "+" -> "con fuerte intensidad.";
                case "VC" -> "en las proximidades.";
                case null -> "con moderada intensidad.";
                default -> null;
            };
            String decoding = phenomena.get() + ' ' + (descriptor.get() != null ? descriptor.get() + ' ' : "") + qualifier;
            setDecoding( new Decoding(List.of(rawSections[0]), decoding));
            return true;
        }
        return false;
    }
}