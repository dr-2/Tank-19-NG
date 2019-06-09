package it.univaq.dr2.tank19.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
@Table(name = "oggettodigioco")
public class OggettoDiGioco extends BaseEntity {

//    @JsonInclude()
//    @Transient

    private Integer posX;
    private Integer posY;

    @Value("${tank.velocita}")
    @Transient
    private Integer velocita; //todo - muovi in app.propr

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void eseguiComando(Comando comando);

    void muovi(Direzione direzione) {
        if (direzione == Direzione.NORD) {
            this.posY = this.posY - 4;
        }
        if (direzione == Direzione.SUD) {
            this.posY = this.posY + 4;
        }
        if (direzione == Direzione.EST) {
            this.posX = this.posX + 4;
        }
        if (direzione == Direzione.OVEST) {
            this.posX = this.posX - 4;
        }
    }

}
