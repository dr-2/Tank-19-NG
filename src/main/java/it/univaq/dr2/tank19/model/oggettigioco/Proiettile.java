package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.ComandoTankStrategyFactory;
import lombok.*;

import javax.persistence.*;

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

    private TipoOggetto tipo = TipoOggetto.PROIETTILE;

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
    public Integer getDimensioneHitbox() {
        return 5;
    }
}
