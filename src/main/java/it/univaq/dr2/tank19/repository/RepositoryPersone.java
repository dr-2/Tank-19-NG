package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPersone extends JpaRepository<Persona, Long> {
    Persona findByUsername(String username);
}