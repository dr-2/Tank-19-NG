package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.messaggi.MessaggioComando;

public interface ServicePartita extends CrudService<Partita, Long> {
    void aggiornaStato(MessaggioComando comando);
}
