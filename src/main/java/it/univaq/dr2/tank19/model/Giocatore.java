package it.univaq.dr2.tank19.model;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name = "Giocatore.findByUsername", query = "SELECT g FROM Giocatore g WHERE g.username = ?1")
@Table(name = "giocatori")
public class Giocatore extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "punteggio_id", referencedColumnName = "id")
    private Punteggio punteggio;

    private String username;
    private String password;
    private String ruolo = "ROLE_GIOCATORE";

    public void aggiungiPunti(Integer punti) { // TODO: cambiare in assegna ricompensa
        this.punteggio.aggiungiPunti(punti);
    }
}
