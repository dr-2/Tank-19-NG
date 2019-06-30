package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partite")
public class Partita extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita")
    private Set<Giocatore> giocatori = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita", fetch = FetchType.EAGER)
    private Set<Tank> tanks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita", fetch = FetchType.EAGER)
    private Set<Proiettile> proiettili = new HashSet<>();

    private TipoStatoPartita stato;
    private Integer numeroGiocatoriUmani;

    public Set<Long> getListaIdGiocatori() {
        Set<Long> idGiocatori = new HashSet<>();
        this.getGiocatori().iterator().forEachRemaining(e -> {
            idGiocatori.add(e.getId());
        });
        return idGiocatori;
    }

    public Set<OggettoDiGioco> getOggettiPartita() {
        Set<OggettoDiGioco> oggetti = new HashSet<>();
        oggetti.addAll(tanks);
        oggetti.addAll(proiettili);

        return oggetti;
    }

}
