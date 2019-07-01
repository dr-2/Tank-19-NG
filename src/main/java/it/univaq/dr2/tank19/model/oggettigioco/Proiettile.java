package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;
import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proiettili")
public class Proiettile extends BaseEntity implements OggettoDiGioco {
    @OneToOne(cascade = CascadeType.ALL)
    private Tank tank;

    Integer hitbox;

    private TipoOggetto tipo = TipoOggetto.PROIETTILE;

    private Direzione direzione;

    private Integer velocita;

    private Integer vita;


    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;

    @Transient
    Comando comando;

    public Integer getXMax() {
        return posX + hitbox;
    }

    public Integer getYMax() {
        return posY + hitbox;
    }

    @Override
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
    }


    public void setComandoFuoco() {
        // do nothing. un proiettile, per ora, non può sparare
    }


    @Override
    public Proiettile getProiettile() { // Al momento, un proiettile non ha altri proiettili
        return null;
    }

    @Override
    public void setProiettile(Proiettile proiettile) {
        // do nothing. un proiettile per ora non può possedere un altro proiettile
    }

    @Override
    public void eseguiComando() {
        comando.esegui(this);
    }


    @Override
    public Partita getPartita() {
        return this.getTank().getPartita();
    }


    @Override
    public Integer getDimensioneHitbox() {
        return hitbox;
    }

    @Override
    public void riduciVita(Integer dannoSubito){
        int newVita = this.getVita() - dannoSubito;
        if (newVita < 0){ // Non non può avere vita negativa
            newVita = 0;
        }
        this.setVita(newVita);
    }

}
