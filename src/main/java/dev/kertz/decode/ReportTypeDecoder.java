package dev.kertz.decode;

import java.util.regex.Matcher;

class ReportTypeDecoder extends Decoder{

    ReportTypeDecoder(){
        super("(METAR|TAF|PRONAREA|SPECI|SIGMET)");
    }

    @Override
    public String decode(String section){
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() )
            return "Reporte de tipo " + matcher.group() + ".";
        else return null;
    }
}
