package dev.kertz.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;


@Entity
@Getter
public class Fir {
	@Id
	private int code;

	@Column(length = 3)
	private String identifier;
	
	private String name;

	@OneToMany(mappedBy = "fir")
	private Set<Airport> airports;
}
