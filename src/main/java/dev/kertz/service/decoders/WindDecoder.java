package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

@Service
public class WindDecoder extends SingleDecoder {

    public WindDecoder(){
        super("(?<direction>\\d{3}|VRB)(?<intensity>\\d{2})(G(?<gusts>\\d+))?KT");
    }

    @Override
    public Optional<Decoding> decode(String section) {
        Matcher matcher = super.getMatcher(section);

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
            return Optional.of( new Decoding(decodification, section));
        }
        return Optional.empty();
    }
}
