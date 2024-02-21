package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class TemperatureDecoder extends Decoder {

    TemperatureDecoder(){
        //super("(?<temperature>M?\\d{2})/(?<dewPoint>M?\\d{2})");
        super("((?<temperature>M?\\d{2})/(?<dewPoint>M?\\d{2})|TX(?<maxTemp>M?\\d{2})/(?<maxTempDate>\\d{2})(?<maxTempTime>\\d{2})Z|TN(?<minTemp>M?\\d{2})/(?<minTempDate>\\d{2})(?<minTempTime>\\d{2})Z)");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            String decodification;
            if( matcher.group("temperature") != null ) {
                int temperature = parseInt(matcher.group("temperature").replace('M', '-'));
                int dewPoint = parseInt(matcher.group("dewPoint").replace('M', '-'));
                decodification =  "La temperatura es de " + temperature + "°C, y el punto de rocío es de " + dewPoint + "°C.";
            }
            else if( matcher.group("maxTemp") != null ){
                String maxTemp = parseInt(matcher.group("maxTemp").replace('M', '-')) + "°C";
                int maxTempDate = parseInt(matcher.group("maxTempDate"));
                String maxTempTime = matcher.group("maxTempTime") + ":00 UTC";
                decodification =  "Se espera una temperatura máxima de " + maxTemp + " el día " + maxTempDate + " a las " + maxTempTime + ".";
            }
            else{
                String minTemp = parseInt(matcher.group("minTemp").replace('M', '-')) + "°C";
                int minTempDate = parseInt(matcher.group("minTempDate"));
                String minTempTime = matcher.group("minTempTime") + ":00 UTC";
                decodification =  "Se espera una temperatura mínima de " + minTemp + " el día " + minTempDate + " a las " + minTempTime + ".";
            }
            return Optional.of(new Decodification(List.of(section), decodification));
        }
        return Optional.empty();
    }
}