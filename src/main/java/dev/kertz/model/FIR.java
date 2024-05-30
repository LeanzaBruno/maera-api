package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FIR {

	@Id
	@Column(length = 4)
	private String icao;
	private String name;
	@OneToOne private Airport capitalAirport;
}
