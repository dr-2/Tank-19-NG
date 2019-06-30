package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import lombok.NonNull;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.awt.*;

public class LimiteMappa implements OggettoDiGioco {

    private TipoOggetto tipo = TipoOggetto.LIMITE;
    private Integer velocita = 0;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;


    @Override
    public void setDirezione(Direzione direzione) {

    }

    @Override
    public void setComandoMovimento() {

    }

    @Override
    public void setComandoFuoco() {

    }

    @Override
    public Proiettile getProiettile() {
        return null;
    }

    @Override
    public Direzione getDirezione() {
        return null;
    }

    @Override
    public void setProiettile(Proiettile proiettile) {

    }

    @Override
    public void eseguiComando() {

    }

    @Override
    public Integer getVelocita() {
        return null;
    }

    @Override
    public Partita getPartita() {
        return null;
    }

    @Override
    public Integer getDimensioneHitbox() {
        return 1;
    }

    @Override
    public Integer getPosX() {
        return null;
    }

    @Override
    public Integer getPosY() {
        return null;
    }

    @Override
    public Integer getXMax() {
        return null;
    }

    @Override
    public Integer getYMax() {
        return null;
    }

    @Override
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    @Override
    public Long getId() {
        return this.getId();
    }

    @Override
    public TipoOggetto getTipo() {
        return this.getTipo();
    }

    @Override
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
    }
}
