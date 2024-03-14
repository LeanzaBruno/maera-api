package dev.kertz.service.decoders;

import dev.kertz.dto.Decoding;
import java.util.Optional;

public abstract class MultiDecoder {

    public abstract Optional<Decoding> decode(String ... sections);
}
