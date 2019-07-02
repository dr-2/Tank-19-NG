package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Carlo Centofanti
 * @created 09/06/2019
 */
@Repository
public interface RepositoryTank extends CrudRepository<Tank, Long> {
}
