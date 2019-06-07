package it.univaq.dr2.tank19.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Carlo Centofanti
 * @created 07/06/2019
 */

@Data
@Entity
public class Punteggio extends BaseEntity {
    private Integer punti;

    @OneToOne(mappedBy = "punteggio")
    private Giocatore giocatore;

    public void aggiungiPunti(Integer punti) {
        this.punti += punti;
        if (this.punti < 0) this.punti = 0;
    }
}
