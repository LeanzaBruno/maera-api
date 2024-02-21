package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Data;

// TODO agregar aeropuerto?
// TODO guardar tmb la fecha si quiero guardarlo en una base de datos
@Entity
@Data
public class Metar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private final String raw;

    @ManyToOne
    @JoinColumn(name="airport_code", nullable = false)
    private final Airport airport;
}

