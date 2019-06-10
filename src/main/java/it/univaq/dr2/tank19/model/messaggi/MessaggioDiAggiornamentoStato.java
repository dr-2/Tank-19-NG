package it.univaq.dr2.tank19.model.messaggi;

import it.univaq.dr2.tank19.model.Direzione;
import lombok.Data;

/**
 * @author Carlo Centofanti
 * @created 09/06/2019
 */
@Data
public class MessaggioDiAggiornamentoStato extends MessaggioBase {
    private Long idOggetto;
    private Integer posx;
    private Integer posy;
    private Direzione direzione;
}
