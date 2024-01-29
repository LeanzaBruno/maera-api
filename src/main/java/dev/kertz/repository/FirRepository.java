package dev.kertz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import dev.kertz.model.Fir;

public interface FirRepository extends CrudRepository<Fir, String> {
	Optional<Fir> findByCodeIgnoreCase(String code);
}
