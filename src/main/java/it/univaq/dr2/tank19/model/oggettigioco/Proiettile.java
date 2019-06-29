package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.ComandoTankStrategyFactory;
import lombok.*;

import javax.persistence.*;
import java.awt.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "proiettili")
public class Proiettile extends BaseEntity implements OggettoDiGioco {
    @OneToOne(cascade = CascadeType.ALL)
    private Tank tank;


    private Direzione direzione;

    private Integer velocita = 4;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;

    @Transient
    Comando comando;

    public Integer getXMax() {
        return posX + 5;
    }

    public Integer getYMax() {
        return posY + 5;
    }

    @Override
    public void setComandoMovimento() {
        ComandoTankStrategyFactory factoryComandiTank = ComandoTankStrategyFactory.getInstance();
        this.comando = factoryComandiTank.getComandoMovimento();
    }

    @Override
    public void setComandoFuoco() {
        // do nothing. un proiettile, per ora, non può sparare
    }

    @Override
    public Proiettile getProiettile() {
        return null;
    }

    @Override
    public void setProiettile(Proiettile proiettile) {
        // do nothing. un proiettile per ora non puuò possedere un altro proiettile
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
    public Boolean collidoCon(OggettoDiGioco altroOggetto) {
        /**
         *
         *   x1,y1-------x2,y1
         *   |              |
         *   |              |
         *   |              |
         *   x1,y2-------x2,y2
         *
         */

        int x1 = this.getPosX();
        int x2 = this.getXMax();
        int y1 = this.getPosY();
        int y2 = this.getYMax();


        Polygon questo = new Polygon();
        questo.addPoint(x1, y1);
        questo.addPoint(x2, y1);
        questo.addPoint(x1, y2);
        questo.addPoint(x2, y2);


        int x_1 = altroOggetto.getPosX();
        int x_2 = altroOggetto.getXMax();
        int y_1 = altroOggetto.getPosY();
        int y_2 = altroOggetto.getYMax();

        Polygon altro = new Polygon();
        altro.addPoint(x_1, y_1);
        altro.addPoint(x_2, y_1);
        altro.addPoint(x_1, y_2);
        altro.addPoint(x_2, y_2);

        if (questo.getBounds().intersects(altro.getBounds())) {
            System.out.println("COLLISIONE!!!!");
            return true;
        } else return false;

    }

    @Override
    public Integer getDimensioneHitbox() {
        return 5;
    }
}
