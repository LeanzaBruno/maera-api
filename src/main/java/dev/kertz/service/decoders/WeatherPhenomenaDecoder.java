package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import dev.kertz.enums.WeatherDescriptor;
import dev.kertz.enums.WeatherPhenomena;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.stream.Stream;

@Service
public class WeatherPhenomenaDecoder extends SingleDecoder {

    public WeatherPhenomenaDecoder(){
        super("(?<qualifier>[+-]|VC)?(?<phenomena>DZ|RA|SN|PL|GR|GS|BR|FG|FU|VA|DU|TS|SA|HZ|PO|SQ|FC|SS|DS)(?<descriptor>\\w{2})?\b");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

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
            return Optional.of( new Decoding(decoding, section));

        }
        return Optional.empty();
    }
}