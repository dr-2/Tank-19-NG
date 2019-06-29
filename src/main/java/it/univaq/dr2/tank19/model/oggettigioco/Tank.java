package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Giocatore;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.ComandoTankStrategyFactory;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Component
@Table(name = "tank")
public class Tank extends BaseEntity implements OggettoDiGioco {

//    @JsonInclude()
//    @Transient

    @Transient
    Comando comando;


    private Direzione direzione;
    private String tipo;
    private Integer velocita;

    @OneToOne(cascade = CascadeType.ALL)
    private Giocatore proprietario;

    @OneToOne(cascade = CascadeType.ALL)
    private Proiettile proiettile;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;

    public Integer getXMax() {
        return posX + 30;
    }

    public Integer getYMax() {
        return posY + 30;
    }

    @Override
    public void eseguiComando() {
        comando.esegui(this);
    }

    @Override
    public Integer getVelocita() {
        return this.velocita;
    }

    @Override
    public Boolean collidoCon(OggettoDiGioco altroOggetto) {
        return null;
    }

    @Override
    public Integer getDimensioneHitbox() {
        return 30;
    }

    @Override
    public void setComandoMovimento() {
        ComandoTankStrategyFactory factoryComandiTank = ComandoTankStrategyFactory.getInstance();
        this.comando = factoryComandiTank.getComandoMovimento();
    }

    @Override
    public void setComandoFuoco() {
        ComandoTankStrategyFactory factoryComandiTank = ComandoTankStrategyFactory.getInstance();
        this.comando = factoryComandiTank.getComandoFuoco();
    }

}
