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

    // workaround per iniettare il valore nella variabile statica di classe
    @Value("${tank.velocita}")
    public void setVelocita(Integer v) {
        velocita = v;
    }

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void eseguiComando(Comando comando);

    void muovi(Direzione direzione) {
        if (direzione == Direzione.NORD) {
            this.posY = this.posY - OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.SUD) {
            this.posY = this.posY + OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.EST) {
            this.posX = this.posX + OggettoDiGioco.velocita;
        }
        if (direzione == Direzione.OVEST) {
            this.posX = this.posX - OggettoDiGioco.velocita;
        }
    }

}
