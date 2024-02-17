package dev.kertz.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

// TODO agregar aeropuerto?
// TODO guardar tmb la fecha si quiero guardarlo en una base de datos
@Data
public class Metar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private final String raw;

    @ManyToOne
    private Airport airport;
}

