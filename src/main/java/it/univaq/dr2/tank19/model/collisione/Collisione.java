package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public interface Collisione {
    OggettoDiGioco staCollidendoCon(OggettoDiGioco oggettoMosso);
}
