package it.univaq.dr2.tank19.model.gioco;

import it.univaq.dr2.tank19.model.Comando;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.Posizione;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Component
@Table(name = "tank")
public class Tank extends OggettoDiGioco {

//    @JsonInclude()
//    @Transient

    @Transient
    Comando comando;

    @OneToOne(cascade = CascadeType.ALL)
    private Posizione posizione;

    private Direzione direzione;
    private String tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Proiettile proiettile;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void doMossa(Comando comando);

    public void eseguiComando(Direzione direzione) {
        comando.esegui(this, direzione);
    }

}
