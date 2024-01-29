package dev.kertz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Airport {
	@Id
	@Column(length = 4)
	private String ICAO;

	@Column(length = 3)
	private String ANAC;

	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fir")
	private Fir fir;
	
	private boolean hasTAF;

	private boolean hasPRONAREA;
	
	public String getFir() {
		return fir.getName();
	}
}
