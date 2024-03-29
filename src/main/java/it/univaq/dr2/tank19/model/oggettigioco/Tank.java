package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.*;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.FactoryComandiImpl;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.awt.*;

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
@Slf4j
@Table(name = "tank")
public class Tank extends BaseEntity implements OggettoDiGioco {

//    @JsonInclude()
//    @Transient

    @Transient
    Comando comando;


    private Direzione direzione;
    private TipoOggetto tipo = TipoOggetto.CARRO_ARMATO;
    private Integer velocita;
    private Integer vita;

    Integer hitbox;

    @OneToOne(cascade = CascadeType.ALL)
    private Giocatore proprietario;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Proiettile proiettile;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;

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

    @Override
    public void riduciVita() {
        vita = vita - 1;
        log.info("Tank id=" + getId() + " vita: " + vita);
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
    public Integer getDimensioneHitbox() {
        return hitbox;
    }

    @Override
    public Tank getTank() {
        return this;
    }

    @Override
    public void setComandoFuoco() {
        FactoryComandiImpl factoryComandiTank = FactoryComandiImpl.getInstance();
        this.comando = factoryComandiTank.getComandoFuoco();
    }

}
