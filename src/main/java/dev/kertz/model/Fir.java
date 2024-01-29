package dev.kertz.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Fir {
	@Id
	@Column(length = 4)
	private String code;
	
	private String name;
	
	@OneToMany(mappedBy = "fir")
	private Set<Airport> airports;
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
