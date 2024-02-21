package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

// TODO Agergar localidad

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

	@OneToMany(mappedBy = "airport")
	private List<Metar> metars = new ArrayList<>(10);

	@OneToMany(mappedBy = "airport")
	private List<Taf> tafs = new ArrayList<>(10);

	private boolean hasTAF;

	private boolean hasPRONAREA;
	
	public String getFir() {
		return fir.getName();
	}

}
