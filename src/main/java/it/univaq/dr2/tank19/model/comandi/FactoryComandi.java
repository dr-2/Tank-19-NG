package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;

public interface FactoryComandi {
    Comando getComandoMuoviA(Direzione direzione);

    Comando getComandoFuoco();
}
