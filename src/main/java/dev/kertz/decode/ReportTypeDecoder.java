package dev.kertz.decode;

import dev.kertz.enums.Reports;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Getter
public class ReportTypeDecoder extends Decoder implements NotReusable {
    private final static Pattern REPORT_PAT = Pattern.compile("(?<type>METAR|TAF|SPECI|PRONAREA)");
    private final static Pattern POSTFIX_PAT = Pattern.compile("(?<postfix>NIL|AMD|COR)");

    private boolean used = false;

    public ReportTypeDecoder(){
        super();
    }

    @Override
    public boolean decode(String[] rawSections){
        Matcher typeMatcher = REPORT_PAT.matcher(rawSections[0]);
        if( typeMatcher.find() ){
            AtomicReference<String> decoding = new AtomicReference<>();
            Stream.of(Reports.Type.values() )
                    .filter( type -> type.getName().equals(typeMatcher.group("type")) )
                    .findFirst()
                    .ifPresent( type -> decoding.set(type.getMeaning()));

            Matcher postfixMatcher = POSTFIX_PAT.matcher(rawSections[1]);
            if(postfixMatcher.find()){
                decoding.set( decoding.get() + ' ' + postfixMatcher.group("postfix") + '.' );
                setDecoding(new Decoding(List.of(rawSections[0], rawSections[1]) , decoding.get()) );
            }
            else
                setDecoding(new Decoding(List.of(rawSections[0]) , decoding.get() + '.' ));
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