package dev.kertz.decode;
import java.util.List;
import java.util.Optional;

public class CealingAndVisibilityOKDecoder extends Decoder {

    CealingAndVisibilityOKDecoder(){
        super("CAVOK");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        return super.getMatcher(section).find()
                ? Optional.of(new Decodification(List.of(section), "Visibilidad mayor a 10 km, sin nubes por debajo de 5000 ft sin CB ni TCU, y no hay fenómenos meteorólogicos significativos."))
                : Optional.empty();
    }
}