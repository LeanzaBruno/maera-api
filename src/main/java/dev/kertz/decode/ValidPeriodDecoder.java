package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;

@Getter
public class ValidPeriodDecoder extends SingleSectionDecoder implements NotReusable {

    private boolean used = false;

    public ValidPeriodDecoder(){
        super("(?<fromDate>\\d{2})(?<fromTime>\\d{2})/(?<toDate>\\d{2})(?<toTime>\\d{2})");
    }


    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);

        if (matcher.find()) {
            String fromDate = matcher.group("fromDate");
            String fromTime = matcher.group("fromTime") + ":00 UTC";
            String toDate = matcher.group("toDate");
            String toTime = matcher.group("toTime") + ":00 UTC";

            setDecoding(new Decoding(List.of(rawSections[0]), "El reporte tiene período de validez desde la hora " + fromTime + " del día " + fromDate + ", hasta la hora " + toTime + " del día " + toDate + "."));
            return true;
        }
        return false;
    }

    public void markAsUsed(){
        used = true;
    }

    public boolean wasUsed(){
        return used;
    }

    public void markAsUnused(){
        used = false;
    }
}
