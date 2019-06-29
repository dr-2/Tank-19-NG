package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.Posizione;
import it.univaq.dr2.tank19.model.TipoOggetto;

public interface Collisione {
    Boolean isCollisione(Long idOggetto, TipoOggetto tipoOggetto, Posizione posizione);
}
