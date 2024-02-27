package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Getter
public class MaxTemperatureDecoder extends SingleSectionDecoder implements NotReusable {
    private boolean used = false;

    public MaxTemperatureDecoder(){
        super("TX(?<temp>M?\\d{2})/(?<tempDate>\\d{2})(?<tempTime>\\d{2})Z");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);

        if( matcher.find() ){
            String temp = parseInt(matcher.group("temp").replace('M', '-')) + "°C";
            int tempDate = parseInt(matcher.group("tempDate"));
            String tempTime = matcher.group("tempTime") + ":00 UTC";
            String decodification =  "Se espera una temperatura máxima de " + temp + " el día " + tempDate + " a las " + tempTime + ".";
            setDecoding(new Decoding(List.of(rawSections[0]), decodification));
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
