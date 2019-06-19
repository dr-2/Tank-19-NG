package it.univaq.dr2.tank19.model;

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
@Table(name = "oggettodigioco")
public class OggettoDiGioco extends BaseEntity {

//    @JsonInclude()
//    @Transient

    @OneToOne(cascade = CascadeType.ALL)
    private Posizione posizione;

    private String direzione;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void eseguiComando(Comando comando);

    void muovi(Direzione direzione) {
        Movimento movimento = new Movimento();
        movimento.muovimi(this, direzione);
        this.direzione = direzione.toString();

    }

}
