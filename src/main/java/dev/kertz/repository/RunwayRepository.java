package dev.kertz.repository;

import dev.kertz.model.Airport;
import dev.kertz.model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RunwayRepository extends JpaRepository<Runway, Long> {

    List<Runway> findByAirport(Airport airport);
}

