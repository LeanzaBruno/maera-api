package dev.kertz.dto;

import dev.kertz.decode.Decodification;

import java.util.List;

public record ReportDTO(String raw, List<Decodification> decodifications) { }
