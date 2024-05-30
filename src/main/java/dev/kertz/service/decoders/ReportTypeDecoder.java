package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import dev.kertz.enums.Reports;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class ReportTypeDecoder extends MultiDecoder {
    private final static Pattern REPORT_PAT = Pattern.compile("(?<type>METAR|TAF|SPECI|PRONAREA|SIGMET)");
    private final static Pattern POSTFIX_PAT = Pattern.compile("(?<postfix>NIL|AMD|COR)");

    @Override
    public Optional<Decoding> decode(String[] sections){
        Matcher typeMatcher = REPORT_PAT.matcher(sections[0]);
        if( typeMatcher.find() ){
            AtomicReference<String> decoding = new AtomicReference<>();
            Stream.of(Reports.Type.values() )
                    .filter( type -> type.name().equals(typeMatcher.group("type")) )
                    .findFirst()
                    .ifPresent( type -> decoding.set(type.getMeaning()));

            Matcher postfixMatcher = POSTFIX_PAT.matcher(sections[1]);
            if(postfixMatcher.find()){
                decoding.set( decoding.get() + ' ' + postfixMatcher.group("postfix") + '.' );
                return Optional.of(new Decoding(decoding.get(), sections[0], sections[1]));
            }
            else
                return Optional.of(new Decoding(decoding.get() + '.', sections[0]));
        }
        return Optional.empty();
    }
}