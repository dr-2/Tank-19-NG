package it.univaq.dr2.tank19.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Partita {
    @Id
    @GeneratedValue
    Long id;

    String giocatoreA;
    String giocatoreB;
}
