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

    @Transient
    Comando comando;

    @OneToOne(cascade = CascadeType.ALL)
    private Posizione posizione;

    private Direzione direzione;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    //void doMossa(Comando comando);

    public void eseguiComando(Direzione direzione) {
        comando.esegui(this, direzione);


    }

}
