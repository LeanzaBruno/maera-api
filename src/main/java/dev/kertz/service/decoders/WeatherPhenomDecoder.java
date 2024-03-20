package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import dev.kertz.weather.Descriptor;
import dev.kertz.weather.Phenom;
import dev.kertz.weather.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;

@Service
public class WeatherPhenomDecoder extends SingleDecoder {

    public WeatherPhenomDecoder(){
        super("(?<qualifier>[+-]|VC)?(?<descriptor>MI|BC|PR|DR|BL|SH|TS|FZ)?(?<phenom>DZ|RA|SN|PL|GR|GS|BR|FG|FU|VA|DU|SA|HZ|(?<!TEM)PO|SQ|FC|SS|DS)");
    }

    @Override
    public Optional<Decoding> decode(String section){
        Matcher matcher = super.getMatcher(section);

        if(matcher.find()){
            Qualifier qualifier = getQualifier(matcher.group("qualifier"));
            Descriptor descriptor = getDescriptor(matcher.group("descriptor"));
            Phenom phenom = getPhenom(matcher.group("phenom"));

            String decoding = phenom.description() + (descriptor != null ? descriptor.description() : "") + qualifier.description();

            return Optional.of( new Decoding(decoding, section));
        }
        return Optional.empty();
    }

    private Phenom getPhenom(String target){
        AtomicReference<Phenom> targetRef = new AtomicReference<>();
        Arrays.stream(Phenom.values())
                .filter( ph -> ph.name().equals(target))
                .findFirst()
                .ifPresent(targetRef::set);
        return targetRef.get();
    }

    private Descriptor getDescriptor(String target){
        AtomicReference<Descriptor> descriptorRef = new AtomicReference<>();
        Arrays.stream(Descriptor.values())
                .filter( descriptor -> descriptor.name().equals(target))
                .findFirst()
                .ifPresent(descriptorRef::set);
        return descriptorRef.get();
    }

    private Qualifier getQualifier(String target){
        AtomicReference<Qualifier> qualifierRef = new AtomicReference<>();
        Arrays.stream(Qualifier.values())
                .filter( qualifier -> qualifier.symbol().equals(target))
                .findFirst()
                .ifPresentOrElse(qualifierRef::set, () -> qualifierRef.set(Qualifier.MODERATE));
        return qualifierRef.get();
    }
}