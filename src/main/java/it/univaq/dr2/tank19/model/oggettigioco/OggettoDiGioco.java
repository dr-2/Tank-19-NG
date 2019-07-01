package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;

import java.awt.*;

public interface OggettoDiGioco {
    void setDirezione(Direzione direzione);

    void setComando(Comando comando);

    Tank getTank();

    void setComandoFuoco();

    Proiettile getProiettile();

    Direzione getDirezione();

    void setProiettile(Proiettile proiettile);

    void eseguiComando();

    void setVita(int newVita);

    void scalaVita(int danno);

    Integer getVita();

    void setDanno(int newDanno);

    Integer getDanno();

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

    void riduciVita();

    Integer getVita();
}
