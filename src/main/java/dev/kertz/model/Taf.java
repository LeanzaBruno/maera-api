package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Taf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private final String raw;

    @ManyToOne
    private final Airport airport;
}
