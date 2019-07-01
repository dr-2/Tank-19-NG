package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public class RegolaCollisioneTankMuretto implements RegolaCollisione {
    @Override
    public void applicaEffetto(OggettoDiGioco ogg1, OggettoDiGioco ogg2) {
        ogg1.riduciVita(0);
        ogg2.riduciVita(0);
    }
}
