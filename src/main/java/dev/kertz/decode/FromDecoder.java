package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class FromDecoder extends SingleSectionDecoder {
    public FromDecoder(){
        super("FM(?<date>\\d{2})(?<hh>\\d{2})(?<mm>\\d{2})");
    }

    @Override
    public boolean decode(String[] sections){
        Matcher matcher = getMatcher(sections[0]);

        if(matcher.find()){
            int date = parseInt( matcher.group("date") );
            String time = matcher.group("hh") + ":" + matcher.group("mm") + " UTC";
            setDecoding(new Decoding(
                            List.of(sections[0]),
                            "A partir de las " + time + " del día " + date +", se espera un cambio permanente de las condiciones meteorológicas." )
            );
            return true;
        }
        return false;
    }
}
