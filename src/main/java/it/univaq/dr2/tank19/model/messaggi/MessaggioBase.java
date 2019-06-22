package it.univaq.dr2.tank19.model.messaggi;

import lombok.Data;

@Data
public abstract class MessaggioBase {
    private TipoMessaggio tipoMessaggio;
    //private String sender;
    private Long idPartita;
}
