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

    private TipoOggetto tipo = TipoOggetto.PROIETTILE;

    private Direzione direzione;

    private Integer velocita = 4;
    private Integer vita = 1; // Quanti danni può subire il proiettile
    private Integer danno = 2; // Il danno che arreca la collisione con un proiettile ad un altro oggetto

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
        return posX + 5;
    }

    public Integer getYMax() {
        return posY + 5;
    }


    @Override
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
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
        return 5;
    }

    @Override
    public void setVita(int newVita){
        this.vita = newVita;
    }

    @Override
    public void scalaVita(int danno){
        int newVita = this.getVita() - danno;
        if (newVita < 0){ // Il proiettile non può avere vita negativa
            newVita = 0;
            // ToDo: Aggiungere comportamento sparizione proiettile
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
