package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.Giocatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryGiocatore extends JpaRepository<Giocatore, Long> {

    public Giocatore findByUsername(String username);
}
