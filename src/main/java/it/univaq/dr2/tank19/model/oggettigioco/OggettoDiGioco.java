package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;

import java.awt.*;

public interface OggettoDiGioco {
    // Composition & Aggregation, fuori da questa interfaccia please!
    void setDirezione(Direzione direzione);

    void setComando(Comando comando);

    void setComandoFuoco();

    Proiettile getProiettile();

    void setProiettile(Proiettile proiettile);

    Direzione getDirezione();

    void eseguiComando();

    Integer getVelocita();

    Partita getPartita();

    Integer getDimensioneHitbox();

    Integer getPosX();

    Integer getPosY();

    Integer getXMax();

    Integer getYMax();

    void setPosX(Integer posX);

    void setPosY(Integer posY);

    Long getId();

    TipoOggetto getTipo();

    Polygon getPolygon();

    void riduciVita(Integer dannoSubito);

    Integer getVita();
}