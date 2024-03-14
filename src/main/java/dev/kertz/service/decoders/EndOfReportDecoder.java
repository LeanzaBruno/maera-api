package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class EndOfReportDecoder extends SingleDecoder {

    public EndOfReportDecoder(){
        super("=");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);
        if(matcher.find()){
            return Optional.of(new Decoding(section, "Fin del reporte"));
        }
        return Optional.empty();
    }
}
