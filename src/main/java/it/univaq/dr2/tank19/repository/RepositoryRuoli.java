package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.RuoloPersona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRuoli extends JpaRepository<RuoloPersona, Long> {
}
