package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.Posizione;

public interface OggettoDiGioco {
    void setDirezione(Direzione direzione);

    void setComandoMovimento();

    void setComandoFuoco();

    Proiettile getProiettile();

    Direzione getDirezione();

    Posizione getPosizione();

    void setProiettile(Proiettile proiettile);

    void eseguiComando();

    void setPosizione(Posizione posizione);

    Integer getVelocita();

    Partita getPartita();

    Boolean collidoCon(OggettoDiGioco altroOggetto);

    Integer getDimensioneHitbox();
}
