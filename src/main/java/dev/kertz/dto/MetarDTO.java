package dev.kertz.dto;
import dev.kertz.decode.Decodification;

import java.util.List;

public record MetarDTO(String raw, List<Decodification> sections) { }