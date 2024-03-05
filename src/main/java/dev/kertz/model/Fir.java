package dev.kertz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
@Getter
public class Fir {

	@Id
	@Column(length = 3)
	private String identifier;

	private int wmo;
	
	private String name;
}
