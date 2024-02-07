package dev.kertz.core;

import dev.kertz.model.Metar;
import dev.kertz.model.TemperatureInfo;
import dev.kertz.model.WindInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetarDecoder {

    public static void decode(Metar metar){
        final String raw = metar.getRaw();

//        Pattern pattern = Pattern.compile(
//                "(\\w{4}) (\\d{2})(\\d{4})Z (VRB\\d{2}|\\d{5}|\\d{5}G\\d{2})KT(.*)(\\d{2})/(\\d{2}) Q(\\d{4}) (.*)");
        Pattern pattern = Pattern.compile(
                "(\\w{4}) (\\d{2})(\\d{4})Z (\\d{3})(\\d{2})(?:G(.*))?KT(.*)(\\d{2})/(\\d{2}) Q(\\d{4}) (.*)");
        Matcher matcher = pattern.matcher( raw );
        if( matcher.find() ){
            metar.setAirportCode( matcher.group(1) );
            metar.setDate( matcher.group(2) );
            metar.setTime( matcher.group(3) );

            /*
            String windInfo = matcher.group(4);
            metar.setWindDirection( windInfo.substring(0, 3) );
            metar.setWindIntensity( windInfo.substring(3, 5) );
            if( windInfo.indexOf('G') != -1 ) metar.setWindGusts( windInfo.substring(6) );
             */

            String group7 = matcher.group(7);
            metar.setClouds( getClouds(group7) );
            metar.setCavok( getCAVOK(group7) );
            metar.setVisibility( getVisibility(group7) );
            metar.setNoSignificantClouds( getNSC(group7) );

            metar.setWind(
                    new WindInformation(
                            matcher.group(4),
                            matcher.group(5),
                            matcher.group(6),
                            getWindVariation(group7) ));

            metar.setTemperature( new TemperatureInfo( matcher.group(8), matcher.group(9) ));
            metar.setPressure( matcher.group(10) );

            String group11 = matcher.group(11);
            metar.setNoSignificalChanges( getNOSIG(group11) );
            metar.setRemarks( getRemarks(group11) );
        }
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
