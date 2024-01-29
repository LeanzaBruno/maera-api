package dev.kertz.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.kertz.model.Airport;
import dev.kertz.model.Fir;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
	Optional<Airport> findByICAOIgnoreCase(String code);
	List<Airport> findByFir(Fir fir);
}
