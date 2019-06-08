package it.univaq.dr2.tank19.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    public Set<Long> getListaIdGiocatori() {
        Set<Long> idGiocatori = new HashSet<>();
        this.getGiocatori().iterator().forEachRemaining(e -> {
            idGiocatori.add(e.getId());
        });
        return idGiocatori;
    }
}
