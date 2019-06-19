package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Giocatore;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ServiceGiocatore extends CrudService<Giocatore, Long>, UserDetailsService {
    public Giocatore findByUsername(String username);
}
