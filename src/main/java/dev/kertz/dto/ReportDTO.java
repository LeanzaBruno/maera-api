package dev.kertz.dto;

import java.util.List;

public record ReportDTO(String raw, List<Decoding> decodings) { }
