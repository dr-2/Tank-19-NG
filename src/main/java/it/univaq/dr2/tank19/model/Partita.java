package it.univaq.dr2.tank19.model;

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
    private Set<OggettoDiGioco> oggettiDiGioco = new HashSet<>();


    private TipoStatoPartita stato;
    private Integer numeroGiocatoriUmani;

    public Set<Long> getListaIdGiocatori() {
        Set<Long> idGiocatori = new HashSet<>();
        this.getGiocatori().iterator().forEachRemaining(e -> {
            idGiocatori.add(e.getId());
        });
        return idGiocatori;
    }

    public void muovi(Long idOggetto, Direzione direzione) {
        oggettiDiGioco.iterator().forEachRemaining(oggetto -> {
            if (oggetto.getId().equals(idOggetto)) {
                Comando c = new ComandoMossa();
                oggetto.setComando(c);
                oggetto.eseguiComando(direzione);
            }
        });
    }

}
