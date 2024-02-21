package dev.kertz.decode;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import static java.lang.Integer.parseInt;

public class VisibilityDecoder extends Decoder {
    public VisibilityDecoder(){
        super("(?<!Q)\\d{4}(?!.)");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        Matcher matcher = super.getMatcher(section);

        if (matcher.find()) {
            int visibility = parseInt( matcher.group() );
            return Optional.of( new Decodification(List.of(section), "La visibilidad es de " + (visibility == 9999 ? "más de 10 km." : visibility + " metros.")) );
        }
        
        return Optional.empty();
    }
}
