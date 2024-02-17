package dev.kertz.decode;

import java.util.List;

public class MetarDecoders{


    public static final List<Decoder> list = List.of(
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
            new NoSignificalChangesDecoder(),
            new RemarkDecoder(),
            new EndOfReportDecoder()
    );
}
