package it.univaq.dr2.tank19.repository;

import it.univaq.dr2.tank19.model.Giocatore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryGiocatore extends CrudRepository<Giocatore, Long> {


    @Query("SELECT g FROM Giocatore g WHERE g.username = :username")
    public Giocatore findByUsername(String username);
}
