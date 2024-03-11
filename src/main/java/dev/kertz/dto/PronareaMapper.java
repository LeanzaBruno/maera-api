package dev.kertz.dto;

import dev.kertz.decode.Decoder;
import dev.kertz.decode.Decoding;
import dev.kertz.decode.ReportTypeDecoder;
import dev.kertz.model.Report;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class PronareaMapper {

    private static final List<Decoder> decoders = List.of(
            new ReportTypeDecoder()
    );
    private static final String REGEX_PRONAREA_FOREWORD = "FIR (?<fir>\\w+) VALIDEZ (?<from>\\d{2})(?<to>\\d{2}).* MAPA (?<mapTime>\\d{4})";
    private static final String REGEX_SIGFENOM = ".*SIGFENOM\\s?:?\\s?(?<sigfenom>.*)";
    private static final String REGEX_JETSTREAM = "CORRIENTE EN CHORRO\\s?:?\\s?(?<jetStream>.*)";
    private static final String REGEX_MAX_WIND = "VIENTO.*:\\s?(?<maxWind>.*)";
    private static final String REGEX_TURBULENCE = "TURBULENCIA\\s?:?\\s?(?<turbulence>.*)";
    private static final String REGEX_FREEZING = "ENGELAMIENTO\\s?:?\\s?(?<freezing>.*)";
    private static final String REGEX_ISOTHERM = "ISOTERMA.*GRADOS?\\s?:?\\s?(?<isotherm>.*)";
    private static final String REGEX_TROPOPAUSE = "TROPOPAUSA\\s?:?\\s?(?<tropopause>.*)";
    private static final String REGEX_WIND_TEMP = "WIND/T\\s?:?\\s?(?<windAndTemp>.*)";
    private static final String REGEX_FORECAST = "FCST\\s?:?\\s?(?<forecast>.*)";


    public static ReportDTO toDTO(Report pronarea){
        String raw = pronarea.getRaw();
        /**
         * TODO under work
         List<Decoding> decodings = new LinkedList<>();
         Matcher matcher = pattern.matcher( raw );
        if( matcher.find() ) {
            dto.setFirIdentifier( matcher.group("fir") );
            dto.setValidTimeFrom( matcher.group("from") );
            dto.setValidTimeTo( matcher.group("to") );
            dto.setMapTime( matcher.group("mapTime") );
            dto.setSigFenom( matcher.group("sigfenom") );
            dto.setJetStream( matcher.group("jetStream") );
            dto.setTurbulence( matcher.group("turbulence") );
            dto.setFreezing( matcher.group("freezing") );
            dto.setIsotherm( matcher.group("isotherm") );
            dto.setTropopause( matcher.group("tropopause") );
            dto.setWindAndTemp( matcher.group("windAndTemp") );
            dto.setForecast( matcher.group("forecast") );
        }
         */
        return new ReportDTO(raw, new LinkedList<>());
    }


    private static boolean getNSC(String group){
        Pattern NSCPattern = Pattern.compile("NSC");
        Matcher NSCMatcher = NSCPattern.matcher( group );
        return NSCMatcher.find();
    }


    private static Integer getWindVariationFrom(String conditions){
        Pattern pattern = Pattern.compile("(?<from>\\d{3})(?=V\\d{3})");
        Matcher matcher = pattern.matcher(conditions);
        if(matcher.find()) return parseInt( matcher.group("from") );
        else return null;
    }
    
    private static Integer getWindVariationTo(String conditions){
        Pattern pattern = Pattern.compile("(?<=\\d{3}V)(?<to>\\d{3})");
        Matcher matcher = pattern.matcher(conditions);
        if(matcher.find()) return parseInt( matcher.group("to") );
        else return null;
    }

    private static boolean isCAVOK(String group){
        Pattern cavokPattern = Pattern.compile("CAVOK");
        Matcher cavokMatcher = cavokPattern.matcher( group );
        return cavokMatcher.find();
    }

    private static boolean getNOSIG(String group){
        Pattern nosigPattern = Pattern.compile("NOSIG");
        Matcher nosigMatcher = nosigPattern.matcher( group );
        return nosigMatcher.find();
    }

    private static Integer getVisibility(String group){
        Pattern visibilityPattern = Pattern.compile("(?<visibility>\\d{4})");
        Matcher visibilityMatcher = visibilityPattern.matcher( group );
        if( visibilityMatcher.find() ) return parseInt( visibilityMatcher.group("visibility") );
        else return null;
    }

    private static Integer getPrecipitation(String group){
        Pattern pattern = Pattern.compile("(?<=PP)[0-9]{3}");
        Matcher matcher = pattern.matcher( group );
        if( matcher.find() ) return parseInt( matcher.group() );
        else return null;
    }

}
