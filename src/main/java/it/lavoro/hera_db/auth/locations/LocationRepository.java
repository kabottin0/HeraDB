package it.lavoro.hera_db.auth.locations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByUsername(String username);
    boolean existsByUsername(String username);
}