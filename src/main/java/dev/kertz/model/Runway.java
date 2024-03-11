package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Runway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String designator;

    private int length;

    private int width;

    private String surface;

    @ManyToOne
    private Airport airport;
}
