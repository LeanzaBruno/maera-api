package dev.kertz.decode;

import java.util.List;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class WindDecoder extends SingleSectionDecoder {

    public WindDecoder(){
        super("(?<direction>\\d{3}|VRB)(?<intensity>\\d{2})(G(?<gusts>\\d+))?KT");
    }

    @Override
    public boolean decode(String[] sections) {
        Matcher matcher = super.getMatcher(sections[0]);

        if (matcher.find()) {
            String direction = matcher.group("direction");
            int intensity = parseInt( matcher.group("intensity") );
            String gusts = matcher.group("gusts");

            String decodification;
            if(intensity == 0)
                decodification = "Viento calmo.";
            else decodification ="Viento en superficie "
                    + ( direction.equals("VRB") ? "variable " : "desde los " + direction + "° " )
                    + "con una intensidad de " + intensity + " kt"
                    + ( gusts != null ? ", con ráfagas de hasta " + parseInt( gusts ) + " kt." : "." );
            setDecoding( new Decoding(List.of(sections[0]), decodification));
            return true;
        }
        return false;
    }
}
