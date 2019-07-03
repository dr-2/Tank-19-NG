package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.*;
import it.univaq.dr2.tank19.model.comandi.Comando;
import it.univaq.dr2.tank19.model.comandi.FactoryComandiImpl;
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
@Table(name = "muretto")
public class Muretto extends BaseEntity implements OggettoDiGioco {

//    @JsonInclude()
//    @Transient

    @Transient
    Comando comando;


    private Direzione direzione;
    private TipoOggetto tipo = TipoOggetto.MURETTO;
    private Integer velocita;
    private Integer vita;

    Integer hitbox;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    // @OneToOne(cascade = CascadeType.ALL)
    // private Proiettile proiettile; // Al momento, i muretti non sparano, quindi non hanno proiettili

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
    public void eseguiComando() {
        // Al momento, il muretto resta sempre immobile (è un oggetto passivo)
    }


    @Override
    public Proiettile getProiettile() { // Al momento, un muretto non ha proiettili
        return null;
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
    public void riduciVita(Integer dannoSubito){
        int newVita = this.getVita() - dannoSubito;
        if (newVita < 0){ // Non non può avere vita negativa
            newVita = 0;
        }
        this.setVita(newVita);
    }

    @Override
    public void setProiettile(Proiettile proiettile) {
        // Per ora, il muretto non genera proiettili
    }

    @Override
    public void setComandoFuoco() {
       // Per ora, il muretto non spara
    }


}
