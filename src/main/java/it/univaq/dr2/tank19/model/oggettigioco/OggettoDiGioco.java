package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;

import java.awt.*;

public interface OggettoDiGioco {
    void setDirezione(Direzione direzione);

    void setComandoMovimento();

    void setComandoFuoco();

    Proiettile getProiettile();

    Direzione getDirezione();

    void setProiettile(Proiettile proiettile);

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
}
