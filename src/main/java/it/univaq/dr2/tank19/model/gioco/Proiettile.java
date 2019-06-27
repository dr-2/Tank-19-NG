package it.univaq.dr2.tank19.model.gioco;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Posizione;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "proiettili")
public class Proiettile extends OggettoDiGioco {
    @OneToOne(cascade = CascadeType.ALL)
    private Tank tank;

    @OneToOne(cascade = CascadeType.ALL)
    private Posizione posizione;

    private Direzione direzione;
}
