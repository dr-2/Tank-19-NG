package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.awt.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limiti_mappa")
public class LimiteMappa extends BaseEntity implements OggettoDiGioco {

    private TipoOggetto tipo = TipoOggetto.LIMITE;
    private Integer hitbox;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @Override
    public void setDirezione(Direzione direzione) {

    }

    @Override
    public void setComando(Comando comando) {

    }

    @Override
    public Tank getTank() {
        return null;
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
    public Integer getXMax() {
        return posX + hitbox;
    }

    @Override
    public Integer getYMax() {
        return posY + hitbox;
    }



    @Override
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
    }

    @Override
    public void riduciVita() {

    }

    @Override
    public Integer getVita() {
        return null;
    }
}
