package it.univaq.dr2.tank19.model.messaggi;

import lombok.Data;

/**
 * @author Carlo Centofanti
 * @created 09/06/2019
 */
@Data
public class MessaggioDiStato extends MessaggioBase {
    private Long idOggetto;
    private Integer posx;
    private Integer posy;
}
