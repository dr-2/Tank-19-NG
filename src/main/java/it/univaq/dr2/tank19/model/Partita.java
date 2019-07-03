package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.oggettigioco.*;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita", fetch = FetchType.EAGER)
    private Set<LimiteMappa> limitiMappa = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Muretto> muretti = new HashSet<>();

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
        oggetti.addAll(limitiMappa);
        oggetti.addAll(muretti);

        return oggetti;
    }

}
