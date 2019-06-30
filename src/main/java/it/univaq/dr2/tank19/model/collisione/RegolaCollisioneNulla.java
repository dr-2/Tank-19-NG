package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public class RegolaCollisioneNulla implements RegolaCollisione {
    @Override
    public void applicaEffetto(OggettoDiGioco ogg1, OggettoDiGioco ogg2) {
        // Intenzionalmente vuoto. in questo caso non succede nulla
    }
}
