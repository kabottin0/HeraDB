package it.lavoro.hera_db.voti;

import it.lavoro.hera_db.auth.locations.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findAllByLocation(Location location);
}