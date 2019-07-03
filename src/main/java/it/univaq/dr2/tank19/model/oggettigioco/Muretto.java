package it.univaq.dr2.tank19.model.oggettigioco;

import it.univaq.dr2.tank19.model.BaseEntity;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.comandi.Comando;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.awt.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Component
@Slf4j
@Table(name = "muretto")
public class Muretto extends BaseEntity implements OggettoDiGioco {

    Integer hitbox;
    private TipoOggetto tipo = TipoOggetto.MURETTO;
    private Integer vita;
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
    public Long getId() {
        return null;
    }

    @Override
    public Polygon getPolygon() {
        int xPoly[] = {this.getPosX(), this.getXMax()};
        int yPoly[] = {this.getPosY(), this.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
    }

    @Override
    public void eseguiComando() {
        log.warn("Un muretto non può muoversi... per ora :P");
        // Al momento, il muretto resta sempre immobile (è un oggetto passivo)
    }

    @Override
    public Integer getVelocita() {
        return null;
    }

    @Override
    public Proiettile getProiettile() { // Al momento, un muretto non ha proiettili
        log.warn("Sono un muretto, non posso sparare!!");
        return null;
    }

    @Override
    public void setProiettile(Proiettile proiettile) {
        log.warn("Mi hai chiesto di sparare ma io sono un semplice muretto... Non sono addestrato per questo!");
        // Per ora, il muretto non genera proiettili
    }

    @Override
    public Direzione getDirezione() {
        return null;
    }

    @Override
    public void setDirezione(Direzione direzione) {
        log.warn("Sono un muretto in 2D... non ho una direzione :P");
    }

    @Override
    public Integer getDimensioneHitbox() {
        return hitbox;
    }

    @Override
    public void riduciVita() {
        vita = vita - 1;
    }

    @Override
    public void setComando(Comando comando) {
        log.warn("Sono un muretto, per ora non è previsto che possa eseguire comandi");
        //al momento non sono previste interazioni generate dal muretto
    }

    @Override
    public Tank getTank() {
        return null;
    }

    @Override
    public void setComandoFuoco() {
        // Per ora, il muretto non spara
    }

}
