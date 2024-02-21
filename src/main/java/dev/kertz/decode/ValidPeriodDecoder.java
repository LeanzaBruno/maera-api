package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class ValidPeriodDecoder extends Decoder {

    ValidPeriodDecoder(){
        super("(?<fromDate>\\d{2})(?<fromTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    }


    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            String fromDate = matcher.group("fromDate");
            String fromTime = matcher.group("fromTime") + ":00 UTC";
            String toDate = matcher.group("toDate");
            String toTime = matcher.group("toTime") + ":00 UTC";

            return Optional.of(new Decodification(List.of(section), "El reporte tiene período de validez desde la hora " + fromTime + " del día " + fromDate + ", hasta la hora " + toTime + " del día " + toDate + "."));
        }
        return Optional.empty();
    }
}
