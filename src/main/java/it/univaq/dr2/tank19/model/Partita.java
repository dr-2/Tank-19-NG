package it.univaq.dr2.tank19.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "partite")
public class Partita extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partita")
    private Set<Giocatore> giocatori = new HashSet<>();
}
