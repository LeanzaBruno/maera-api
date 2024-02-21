package dev.kertz.decode;

import java.util.List;
import java.util.Optional;

public class RemarkDecoder extends Decoder {

    RemarkDecoder(){
        super("RMK");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextString) {
        return super.getMatcher(section).find()
                ? Optional.of(new Decodification(List.of(section), "El reporte tiene observaciones adicionales."))
                : Optional.empty();
    }
}