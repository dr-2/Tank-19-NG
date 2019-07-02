package it.univaq.dr2.tank19.model.messaggi;

import it.univaq.dr2.tank19.model.TipoOggetto;
import lombok.Data;


@Data
public class MessaggioDiAggiornamentoStato extends MessaggioBase {
    private Long idOggetto;
    private Integer posx;
    private Integer posy;
    private String direzione;
    private TipoOggetto tipoOggetto;
}
