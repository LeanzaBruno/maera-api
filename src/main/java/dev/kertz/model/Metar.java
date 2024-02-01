package dev.kertz.model;

import lombok.Data;

@Data
public class Metar {
    private final String raw;
    private final Airport airport;
}
