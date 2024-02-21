package dev.kertz.decode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class WindDecoder extends Decoder {

    WindDecoder(){
        super("(?<direction>\\d{3}|VRB)(?<intensity>\\d{2})(G(?<gusts>\\d+))?KT");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            String direction = matcher.group("direction");
            int intensity = parseInt( matcher.group("intensity") );
            String gusts = matcher.group("gusts");

            String decodification;
            if(intensity == 0)
                decodification = "Viento calmo.";
            else decodification ="Viento en superficie "
                    + ( direction.equals("VRB") ? "variable " : "sopla desde los " + direction + "° " )
                    + "con una intensidad de " + intensity + " kt"
                    + ( gusts != null ? ", con ráfagas de hasta " + parseInt( gusts ) + " kt." : "." );
            return Optional.of( new Decodification(List.of(section), decodification));
        }
        return Optional.empty();
    }
}
