package dev.kertz.decode;

import lombok.Getter;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Getter
public class TemperatureDecoder extends SingleSectionDecoder implements NotReusable {

    private boolean used = false;

    public TemperatureDecoder(){
        super("^(?<temperature>M?\\d{2})/(?<dewPoint>M?\\d{2})$");
    }

    @Override
    public boolean decode(String[] rawSections) {
        Matcher matcher = super.getMatcher(rawSections[0]);
        if( matcher.find() ){
            int temperature = parseInt(matcher.group("temperature").replace('M', '-'));
            int dewPoint = parseInt(matcher.group("dewPoint").replace('M', '-'));
            String decodification =  "La temperatura es de " + temperature + "°C, y el punto de rocío es de " + dewPoint + "°C.";
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