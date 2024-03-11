package dev.kertz.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Fir {

	@Id
	@Column(length = 4)
	private String id;
	private String name;
	@OneToOne private Airport capitalAirport;
}
