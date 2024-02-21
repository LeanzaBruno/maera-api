package dev.kertz.decode;

import java.util.List;
import java.util.Optional;

public class NoSignificalChangesDecoder extends Decoder {

    NoSignificalChangesDecoder(){
        super("NOSIG");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        return super.getMatcher(section).find()
                ? Optional.of(new Decodification(List.of(section), "No se esperan cambios significativos en las próximas 2 horas."))
                : Optional.empty();
    }
}