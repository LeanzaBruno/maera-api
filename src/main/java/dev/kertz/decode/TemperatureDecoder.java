package dev.kertz.decode;

import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;

public class TemperatureDecoder extends Decoder{

    TemperatureDecoder(){
        super("(?<temperature>M?\\d{2})/(?<dewPoint>M?\\d{2})");
    }

    @Override
    public String decode(String section) {
        Matcher matcher = super.getMatcher(section);

        if( matcher.find() ){
            int temperature = parseInt( matcher.group("temperature").replace('M', '-') );
            int dewPoint = parseInt( matcher.group("dewPoint").replace('M', '-') );

            return "La temperatura es de " + temperature + "°C, y el punto de rocío es de " + dewPoint + "°C.";
        }
        return null;
    }

}
