package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.gioco.Tank;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "partite")
public class Partita extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita")
    private Set<Giocatore> giocatori = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita", fetch = FetchType.EAGER)
    private Set<Tank> tanks = new HashSet<>();


    private TipoStatoPartita stato;
    private Integer numeroGiocatoriUmani;

    public Set<Long> getListaIdGiocatori() {
        Set<Long> idGiocatori = new HashSet<>();
        this.getGiocatori().iterator().forEachRemaining(e -> {
            idGiocatori.add(e.getId());
        });
        return idGiocatori;
    }

}
