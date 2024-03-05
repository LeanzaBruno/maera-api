package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Metar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private final String raw;

    @ManyToOne
    @JoinColumn(name="airport", nullable = false)
    private final Airport airport;
}

