package dev.kertz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Province {
    @Id
    private String name;
}
