package it.univaq.dr2.tank19.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "giocatori")
public class Giocatore extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    String nome;
}
