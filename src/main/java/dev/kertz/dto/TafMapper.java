package dev.kertz.dto;

import dev.kertz.decode.*;
import dev.kertz.model.Taf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TafMapper {
    private static final List<Decoder> decoders = List.of(
            new EndOfReportDecoder(),
            new ReportTypeDecoder(),
            new AirportDecoder(),
            new PublicationDecoder(),
            new WindDecoder(),
            new ValidPeriodDecoder(),
            new MaxTemperatureDecoder(),
            new MinTemperatureDecoder(),
            new WindVariationDecoder(),
            new VisibilityDecoder(),
            new CealingAndVisibilityOKDecoder(),
            new CloudCoverDecoder(),
            new TemporaryDecoder(),
            new WeatherPhenomenaDecoder(),
            new TemperatureDecoder(),
            new PressureDecoder(),
            new PrecipitationDecoder(),
            new BecomingDecoder(),
            new NoSignificalChangesDecoder(),
            new RemarkDecoder(),
            new FromDecoder()
    );

    public static ReportDTO toDTO(Taf taf){
        resetDecoders();
        final String raw = taf.getRaw();

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
        return new ReportDTO(raw, decodings);
    }

    private static void resetDecoders(){
        decoders.stream()
                .filter(decoder -> ! isReusable(decoder))
                .forEach(decoder -> ((NotReusable) decoder).markAsUnused() );
    }

    private static boolean isReusable(Decoder decoder){
        return ! NotReusable.class.isAssignableFrom(decoder.getClass());
    }

    private static boolean isNotReusableAndNotUsed(Decoder decoder){
        return ! ((NotReusable)decoder).wasUsed();
    }
}