package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Giocatore;

public interface ServiceGiocatore extends CrudService<Giocatore, Long> {
    public Giocatore findByUsername(String username);
}
