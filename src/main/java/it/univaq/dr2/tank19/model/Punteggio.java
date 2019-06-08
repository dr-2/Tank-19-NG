package it.univaq.dr2.tank19.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Carlo Centofanti
 * @created 07/06/2019
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "punteggi")
public class Punteggio extends BaseEntity {
    private Integer punti;

    @OneToOne(mappedBy = "punteggio")
    private Giocatore giocatore;

    public void aggiungiPunti(Integer punti) {
        this.punti += punti;
        if (this.punti < 0) this.punti = 0;
    }
}
