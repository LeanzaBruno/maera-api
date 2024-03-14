package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class ValidPeriodDecoder extends SingleDecoder {

    public ValidPeriodDecoder(){
        super("(?<fromDate>\\d{2})(?<fromTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            String fromDate = matcher.group("fromDate");
            String fromTime = matcher.group("fromTime") + ":00 UTC";
            String toDate = matcher.group("toDate");
            String toTime = matcher.group("toTime") + ":00 UTC";

            return Optional.of(new Decoding("Período de validez desde la hora " + fromTime + " del día " + fromDate + ", hasta la hora " + toTime + " del día " + toDate + ".", section));
        }
        return Optional.empty();
    }
}
