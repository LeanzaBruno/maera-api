package dev.kertz.dto;

import dev.kertz.decode.*;
import dev.kertz.model.Metar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MetarMapper {

    private static final List<Decoder> decoders = List.of(
            new EndOfReportDecoder(),
            new ReportTypeDecoder(),
            new AirportDecoder(),
            new PublicationDecoder(),
            new WindDecoder(),
            new WindVariationDecoder(),
            new VisibilityDecoder(),
            new CealingAndVisibilityOKDecoder(),
            new WeatherPhenomenaDecoder(),
            new CloudCoverDecoder(),
            new TemperatureDecoder(),
            new PressureDecoder(),
            new PrecipitationDecoder(),
            new RemarkDecoder(),
            new NoSignificalChangesDecoder(),
            new BecomingDecoder(),
            new TemporaryDecoder()
    );
    public static MetarDTO toDTO(Metar metar) {
        resetDecoders();
        final String raw = metar.getRaw();

        List<Decoding> decodings = new ArrayList<>();
        String[] list = raw.split(" ");
        AtomicInteger index = new AtomicInteger(0);
        while (index.get() < list.length) {
            String[] undecodedSections = Arrays.copyOfRange(list, index.get(), list.length);
            decoders.stream()
                    .filter(decoder -> isReusable(decoder) || isNotReusableAndNotUsed(decoder) )
                    .filter(decoder -> decoder.decode(undecodedSections) )
                    .findFirst()
                    .ifPresentOrElse(decoder -> {
                        Decoding decoding = decoder.getDecoding();
                        decodings.add( decoding );
                        index.set(index.get() + decoding.getDecodedSections());
                        if( ! isReusable(decoder))
                            ((NotReusable)decoder).markAsUsed();
                    }, index::incrementAndGet );
        }
        return new MetarDTO(raw, decodings);
    }

    private static void resetDecoders(){
        decoders.stream()
                .filter(decoder -> ! isReusable(decoder))
                .forEach(decoder -> ((NotReusable) decoder).markAsUnused() );
    }

    private static boolean isReusable(Decoder decoder){
        return ! NotReusable.class.isAssignableFrom(decoder.getClass());
    }

    private static boolean isNotReusableAndNotUsed(Decoder decoder) {
        return !((NotReusable) decoder).wasUsed();
    }
}