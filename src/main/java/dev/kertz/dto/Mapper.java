package dev.kertz.dto;

import dev.kertz.model.Metar;
import dev.kertz.model.Pronarea;
import dev.kertz.model.Taf;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper {
    private static final String REGEX_AIRPORT = "(?<airport>\\w{4})\\s";
    private static final String REGEX_PUBLICATION = "(?<publicationDate>\\d{2})(?<publicationTime>\\d{4})Z\\s";
    private static final String REGEX_VALID_PERIOD = "(?<validFromDate>\\d{2})(?<validFromTime>\\d{2})/(?<validToDate>\\d{2})(?<validToTime>\\d{2})\\s";
    private static final String REGEX_WIND = "(?<windDirection>\\d{3})(?<windIntensity>\\d{2})(G(?<windGusts>.*))?KT\\s";
    private static final String REGEX_WIND_VARIATION = "((?<windVariationFrom>\\d{3})V(?<windVariationTo>\\d{3}))?\\s";
    private static final String REGEX_CURRENT_CONDITIONS = "(?<currentConditions>.*)\\s";
    private static final String REGEX_TEMPERATURE = "(?<temperature>\\d{2})/(?<dewPoint>\\d{2})\\s";
    private static final String REGEX_QNH = "Q(?<qnh>\\d{4})\\s";
    private static final String REGEX_REMARKS = "(?<remarks>.*) =";
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
                    REGEX_PUBLICATION +
                    REGEX_VALID_PERIOD +
                    REGEX_WIND +
                    REGEX_CURRENT_CONDITIONS +
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

            List<String> currentConditions = getCurrentConditions( matcher.group("currentConditions") );
            dto.setCurrentConditions( matcher.group("currentConditions"));
            dto.setCurrentConditionsArr( currentConditions );

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

    public static MetarDTO toDTO(Metar metar){
        final String raw = metar.getRaw();
        MetarDTO dto = new MetarDTO();

        String regex =
                REGEX_AIRPORT +
                        REGEX_PUBLICATION +
                        REGEX_WIND +
                        REGEX_CURRENT_CONDITIONS +
                        REGEX_TEMPERATURE +
                        REGEX_QNH +
                        REGEX_REMARKS;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher( raw );
        if( matcher.find() ){
            dto.setRaw(raw);
            dto.setAirportCode( matcher.group("airport") );
            dto.setPublicationDate( matcher.group("publicationDate") );
            dto.setPublicationTime( matcher.group("publicationTime") );
            dto.setWindDirection( matcher.group("windDirection") );
            dto.setWindIntensity( matcher.group("windIntensity") );
            dto.setWindGusts( matcher.group("windGusts") );
            dto.setTemperature( matcher.group("temperature") );
            dto.setDewPoint( matcher.group("dewPoint") );
            dto.setQNH( matcher.group("qnh") );

            String currentConditions = matcher.group("currentConditions");
            dto.setWindVariationFrom( getWindVariation(currentConditions) );
            dto.setWindVariationTo(currentConditions);
            dto.setVisibility(currentConditions);
            dto.setClouds( getClouds(currentConditions) );
            dto.setCAVOK( getCAVOK(currentConditions) );
            dto.setNSC( getNSC(currentConditions) );

            String remarks = matcher.group("remarks");
            dto.setRemarks( remarks );
            dto.setNOSIG( getNOSIG(remarks));
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
        System.out.println(raw);
        System.out.println(pattern.pattern());
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

    private static List<String> getCurrentConditions(String current){
        List<String> conditions = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_CAVOK);
        Matcher matcher = pattern.matcher(current);

        for(int i = 0 ; matcher.find() ; ++i )
            conditions.add( matcher.group(0));

        return conditions;
    }


    private static boolean getNSC(String group){
        Pattern NSCPattern = Pattern.compile("NSC");
        Matcher NSCMatcher = NSCPattern.matcher( group );
        return NSCMatcher.find();
    }

    private static List<String> getClouds(String group){
        Pattern cloudsPattern = Pattern.compile("(FEW|BKN|SCT|OVC)\\d{3}(?:CB|TCU)?");
        Matcher cloudsMatcher = cloudsPattern.matcher( group );

        List<String> clouds = new ArrayList<>();
        while( cloudsMatcher.find() )
            clouds.add(cloudsMatcher.group());
        return clouds;
    }

    private static boolean getCAVOK(String group){
        Pattern cavokPattern = Pattern.compile("CAVOK");
        Matcher cavokMatcher = cavokPattern.matcher( group );
        return cavokMatcher.find();
    }

    private static boolean getNOSIG(String group){
        Pattern nosigPattern = Pattern.compile("NOSIG");
        Matcher nosigMatcher = nosigPattern.matcher( group );
        return nosigMatcher.find();
    }

    private static String getRemarks(String group){
        int index = group.indexOf("RMK");
        if(index == -1) return "";
        return group.substring(index + 4, group.length() - 2);
    }

    private static String getVisibility(String group){
        Pattern visibilityPattern = Pattern.compile("(\\d{4})");
        Matcher visibilityMatcher = visibilityPattern.matcher( group );
        if( visibilityMatcher.find() )
            return visibilityMatcher.group(1);
        return null;
    }

    private static String getWindVariation(String group){
        Pattern windVariationPattern = Pattern.compile("\\d{3}V\\d{3}");
        Matcher windVariationMatcher = windVariationPattern.matcher( group );
        if( windVariationMatcher.find() )
            return windVariationMatcher.group();
        return null;
    }


    private static String getPrecipitation(String group){
        Pattern precipitationPattern = Pattern.compile("PP[0-9/]{3}");
        Matcher precipitationMatcher = precipitationPattern.matcher( group );
        if( precipitationMatcher.find() )
            return precipitationMatcher.group();
        return null;
    }

}
