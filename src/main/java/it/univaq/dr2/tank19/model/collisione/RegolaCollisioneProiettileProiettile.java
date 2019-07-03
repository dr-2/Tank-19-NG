package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public class RegolaCollisioneProiettileProiettile implements RegolaCollisione {
    @Override
    public void applicaEffetto(OggettoDiGioco ogg1, OggettoDiGioco ogg2) {
        ogg1.riduciVita();
        ogg2.riduciVita();
    }
}
