package dev.kertz.decode;

import java.util.List;
import java.util.Optional;

public class EndOfReportDecoder extends Decoder {

    EndOfReportDecoder(){
        super("=");
    }

    @Override
    public Optional<Decodification> decode(String section, String nextSection) {
        return super.getMatcher(section).find()
                ? Optional.of(new Decodification(List.of(section), "Fin del reporte"))
                : Optional.empty();
    }
}
