package dev.kertz.dto;
import dev.kertz.decode.Decoding;

import java.util.List;

public record MetarDTO(String raw, List<Decoding> sections) { }