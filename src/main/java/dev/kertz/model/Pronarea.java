package dev.kertz.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Pronarea {
    @Id
    private long id;
    private String raw;
}
