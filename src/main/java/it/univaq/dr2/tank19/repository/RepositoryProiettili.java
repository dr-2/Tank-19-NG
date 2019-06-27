package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.gioco.Proiettile;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryProiettili extends CrudRepository<Proiettile, Long> {
}
