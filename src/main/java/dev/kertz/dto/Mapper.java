package dev.kertz.dto;
import dev.kertz.model.Metar;
import dev.kertz.model.Pronarea;
import dev.kertz.model.Taf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toUnsignedString;

public class Mapper {
    private static final String REGEX_AIRPORT = "(?<airport>\\w{4}) ";
    private static final String REGEX_VALID_PERIOD = "(?<validFromDate>\\d{2})(?<validFromTime>\\d{2})/(?<validToDate>\\d{2})(?<validToTime>\\d{2})\\s";
    private static final String METAR_PUBLICATION = "(?<publicationDate>\\d{2})(?<publicationTime>\\d{4})Z ";
    private static final String METAR_CURRENT_CONDITIONS = "(?<airport>\\w{4}) (?<publicationDate>\\d{2})(?<publicationTime>\\d{4})Z (?<windDirection>\\d{3})(?<windIntensity>\\d{2})(G(?<windGusts>.*))?KT (?<otherData>.*) (?<temperature>\\d{2})/(?<dewPoint>\\d{2}) Q(?<qnh>\\d{4}) (?<expected>.*) RMK (?<remarks>.*) =";
    private static final String REGEX_WIND = "(?<windDirection>\\d{3})(?<windIntensity>\\d{2})(G(?<windGusts>.*))?KT ";
    private static final String REGEX_WIND_VARIATION = "((?<windVariationFrom>\\d{3})V(?<windVariationTo>\\d{3}))?\\s";
    private static final String REGEX_REMARKS = "RMK(?<remarks>.*) =";
    private static final String REGEX_MAX_TEMP = "TX(?<maxTemp>\\d{2})/(?<maxTempDate>\\d{2})(?<maxTempTime>\\d{2})Z\\s";
    private static final String REGEX_MIN_TEMP = "TN(?<minTemp>\\d{2})/(?<minTempDate>\\d{2})(?<minTempTime>\\d{2})Z\\s";
    private static final String REGEX_EXPECTED_CONDITIONS = "(?<expectedConditions>.*)\\s=";
    private static final String REGEX_CAVOK = "CAVOK";
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


    public static TafDTO toDTO(Taf taf){
        final String raw = taf.getRaw();
        TafDTO dto = new TafDTO();
        Pattern pattern = Pattern.compile(
                REGEX_AIRPORT +
                    METAR_PUBLICATION +
                    REGEX_VALID_PERIOD +
                    REGEX_WIND +
                    METAR_CURRENT_CONDITIONS +
                    REGEX_MAX_TEMP +
                    REGEX_MIN_TEMP +
                    REGEX_EXPECTED_CONDITIONS);
        Matcher matcher = pattern.matcher( raw );

        if( matcher.find() ){
            dto.setRaw(raw);
            dto.setAirportCode( matcher.group("airport") );
            dto.setPublicationDate( matcher.group("publicationDate") );
            dto.setPublicationTime( matcher.group("publicationTime") );
            dto.setValidFromDate( matcher.group("validFromDate") );
            dto.setValidFromTime( matcher.group("validFromTime") );
            dto.setValidToDate( matcher.group("validToDate") );
            dto.setValidToTime( matcher.group("validToTime") );
            dto.setWindDirection( matcher.group("windDirection") );
            dto.setWindIntensity( matcher.group("windIntensity") );
            dto.setWindGusts( matcher.group("windGusts") );

            dto.setCurrentConditions( matcher.group("currentConditions"));

            dto.setMaxTemperature( matcher.group("maxTemp") );
            dto.setMaxTemperatureDate( matcher.group("maxTempDate") );
            dto.setMaxTemperatureTime( matcher.group("maxTempTime") );
            dto.setMinTemperature( matcher.group("minTemp") );
            dto.setMinTemperatureDate( matcher.group("minTempDate") );
            dto.setMinTemperatureTime( matcher.group("minTempTime") );
            dto.setExpectedConditions( matcher.group("expectedConditions"));
        }
        return dto;
    }


    public static PronareaDTO toDTO(Pronarea pronarea){
        String raw = pronarea.getRaw();
        PronareaDTO dto = new PronareaDTO();

        Pattern pattern = Pattern.compile(
            REGEX_PRONAREA_FOREWORD +
            REGEX_SIGFENOM +
            REGEX_JETSTREAM +
            REGEX_TURBULENCE +
            REGEX_FREEZING +
            REGEX_ISOTHERM +
            REGEX_TROPOPAUSE +
            REGEX_WIND_TEMP +
            REGEX_FORECAST);
        Matcher matcher = pattern.matcher( raw );

        if( matcher.find() ) {
            dto.setRaw(pronarea.getRaw());
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
        return dto;
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
