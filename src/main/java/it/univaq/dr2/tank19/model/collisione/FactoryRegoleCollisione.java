package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.TipoOggetto;

public interface FactoryRegoleCollisione {
    RegolaCollisione getRegolaPer(TipoOggetto oggettoCheGeneraCollisione, TipoOggetto OggettoCheSubisceCollisione);
}
