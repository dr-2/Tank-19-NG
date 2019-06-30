package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public interface RegolaCollisione {
    void applicaEffetto(OggettoDiGioco ogg1, OggettoDiGioco ogg2);
}
