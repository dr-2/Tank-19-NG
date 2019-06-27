package it.univaq.dr2.tank19.model.gioco;

import lombok.*;

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
    @OneToOne(mappedBy = "proiettile")
    private Tank tank;
}
