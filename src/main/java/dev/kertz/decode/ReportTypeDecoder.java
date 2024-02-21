package dev.kertz.decode;

import dev.kertz.enums.ReportType;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class ReportTypeDecoder extends Decoder {

    public ReportTypeDecoder(){
        super("(?:METAR NIL|METAR|TAF NIL|TAF AMD|TAF COR|TAF|SPECI|PRONAREA)");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection){
        if(nextSection == null)
            return Optional.empty();

        

        Matcher matcher = super.getMatcher(section + ' ' + nextSection);
        if( matcher.find() ){
            // TODO terminar
            var wrapper = new Object(){ ReportType report; };
            Stream.of(ReportType.values() )
                    .filter( reportType -> reportType.getName().equals(matcher.group()) )
                    .findFirst()
                    .ifPresent( reportType -> wrapper.report = reportType );
            return Optional.of(
                    new Decodification(wrapper.report.isCompound() ? List.of(section, nextSection) : List.of(section) , wrapper.report.getMeaning())
            );
        }
        return Optional.empty();
    }
}