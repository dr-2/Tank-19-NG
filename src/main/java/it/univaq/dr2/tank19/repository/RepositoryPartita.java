package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.Partita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPartita extends CrudRepository<Partita, Long> {
}
