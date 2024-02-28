package dev.kertz.dto;

import dev.kertz.decode.Decoding;
import java.util.List;

public record ReportDTO(String raw, List<Decoding> decodings) { }
