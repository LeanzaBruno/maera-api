package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

@Service
public class ValidPeriodDecoder extends MultiDecoder {
    private final Pattern timeGroupPattern = Pattern.compile("(?<fromDate>\\d{2})(?<fromHours>\\d{2})(?<fromMinutes>\\d{2})?/(?<toDate>\\d{2})(?<toHours>\\d{2})(?<toMinutes>\\d{2})?");


    @Override
    public Optional<Decoding> decode(String ... sections) {

        if(sections[0].equals("VALID")){
            Matcher matcher = timeGroupPattern.matcher(sections[1]);
            if (matcher.find())
                return Optional.of(new Decoding(getDecoding(matcher), sections[0], sections[1]));
        }
        else{
            Matcher matcher = timeGroupPattern.matcher(sections[0]);
            if (matcher.find())
                return Optional.of(new Decoding(getDecoding(matcher), sections[0]));
        }
        return Optional.empty();
    }

    private String getDecoding(Matcher matcher){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("'el día' dd 'hora' HH:mm 'UTC'");
        int fromDate = parseInt(matcher.group("fromDate"));
        int fromHours = parseInt(matcher.group("fromHours"));
        int fromMinutes = parseInt(matcher.group("fromMinutes") != null ? matcher.group("fromMinutes") : "0");

        int toDate = parseInt(matcher.group("toDate"));
        int toHours = parseInt(matcher.group("toHours"));
        int toMinutes = parseInt(matcher.group("toMinutes") != null ? matcher.group("toMinutes") : "0");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = LocalDateTime.of(
                LocalDate.of(now.getYear(), now.getMonth().getValue(), fromDate),
                LocalTime.of(fromHours, fromMinutes)
        );
        LocalDateTime to = LocalDateTime.of(
                LocalDate.of(now.getYear(), now.getMonth().getValue(), toDate),
                LocalTime.of(toHours, toMinutes)
        );
        return "Período de validez desde " + from.format(format) + " hasta " + to.format(format);
    }
}
