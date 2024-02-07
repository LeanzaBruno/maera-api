package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Airport {
	@Id
	@Column(length = 4)
	private String ICAO;

	@Column(length = 3)
	private String IATA;

	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fir")
	private Fir fir;

	/*
	@OneToMany
	@Size(min = 0, max = 10)
	private List<Metar> metars = new ArrayList<>(10);
	 */

	private boolean hasTAF;

	private boolean hasPRONAREA;
	
	public String getFir() {
		return fir.getName();
	}

}
