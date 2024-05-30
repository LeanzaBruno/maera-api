package dev.kertz.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.kertz.model.FIR;

public interface FirRepository extends JpaRepository<FIR, String> {
	Optional<FIR> findByIcaoIgnoreCase(String icao);
}
