package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.Punteggio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlo Centofanti
 * @created 07/06/2019
 */
@Repository
public interface RepositoryPunteggio extends CrudRepository<Punteggio, Long> {
}
