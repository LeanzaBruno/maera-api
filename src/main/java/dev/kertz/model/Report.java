package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue
    private UUID id;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String raw;

    @ManyToOne private Airport airport;

    public Report(String type, String raw, Airport airport){
        this.type = type;
        this.raw = raw;
        this.airport = airport;
    }
}
