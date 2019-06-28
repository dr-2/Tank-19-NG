package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.gioco.OggettoDiGioco;

public interface Comando {
    void esegui(OggettoDiGioco oggettoDiGioco);
}
