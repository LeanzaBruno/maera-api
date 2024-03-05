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

	@Column(length = 4)
	private String IATA;

	@Column(length = 3, nullable = false)
	private String anac;

	private int WMO;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String city;

	private boolean hasTAF;

	private int elevation;

	private float latitude;

	private float longitude;

	@ManyToOne
	@JoinColumn(name = "province", nullable = false)
	private Province province;

	@ManyToOne
	@JoinColumn(name = "fir")
	private Fir fir;

	public String getFir() {
		return fir.getName();
	}

	public String getProvince(){
		return province.getName();
	}
}
