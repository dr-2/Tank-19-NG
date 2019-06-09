package it.univaq.dr2.tank19.model;

import lombok.Data;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */

@Data
public class OggettoDiGioco extends BaseEntity {

//    @JsonInclude()
//    @Transient

    private Integer posX;
    private Integer posY;
    private Long idPartita;

    //void eseguiComando(Comando comando);

}
