package it.univaq.dr2.tank19.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "oggettodigioco")
public class OggettoDiGioco extends BaseEntity {

//    @JsonInclude()
//    @Transient

    private Integer posX;
    private Integer posY;

    //    @Value("${tank.velocita}")
    private static Integer velocita;
    private static Integer larghezzaCanvas;
    private static Integer altezzaCanvas;

    // workaround per iniettare il valore nella variabile statica di classe
    @Value("${tank.velocita}")
    public void setVelocita(Integer vel) {
        velocita = vel;
    }

    // workaround per iniettare il valore nella variabile statica di classe
    @Value("${canvas.altezza}")
    public void setAltezzaCanvas(Integer altezza) {
        larghezzaCanvas = altezza;
    }

    // workaround per iniettare il valore nella variabile statica di classe
    @Value("${canvas.larghezza}")
    public void setLarghezzaCanvas(Integer larghezza) {
        altezzaCanvas = larghezza;
    }

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void eseguiComando(Comando comando);

    void muovi(Direzione direzione) {
        if (direzione == Direzione.NORD) {
            if (this.posY > 0) this.posY = this.posY - OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.SUD) {
            if (this.posY < OggettoDiGioco.altezzaCanvas - 20) this.posY = this.posY + OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.EST) {
            if (this.posX < OggettoDiGioco.larghezzaCanvas - 20) this.posX = this.posX + OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.OVEST) {
            if (this.posX > 0) this.posX = this.posX - OggettoDiGioco.velocita;
        }
    }

}
