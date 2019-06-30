package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.*;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.ComandoTankStrategyFactory;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.awt.*;


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
    private TipoOggetto tipo = TipoOggetto.CARRO_ARMATO;
    private Integer velocita = 1;
    private Integer vita = 10; // Quanti danni può subire il Tank
    private Integer danno = 1; // Il danno che arreca il Tank quando collide con un altro oggetto (al momento, è in grado solo di eliminare un proiettile).

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
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
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
        return 30;
    }

    @Override
    public Tank getTank() {
        return this;
    }

    @Override
    public void setComandoFuoco() {
        ComandoTankStrategyFactory factoryComandiTank = ComandoTankStrategyFactory.getInstance();
        this.comando = factoryComandiTank.getComandoFuoco();
    }

    @Override
    public void setVita(int newVita){
        this.vita = newVita;
    }

    @Override
    public void scalaVita(int danno){
        int newVita = this.getVita() - danno;
        if (newVita < 0){ // Il Tank non può avere vita negativa
            newVita = 0;
        }
        this.setVita(newVita);
    }

    @Override
    public Integer getVita(){
        return this.vita;
    }

    @Override
    public Integer getDanno(){
        return this.danno;
    }

    @Override
    public void setDanno(int newDanno){
        this.danno = newDanno;
    }


}
