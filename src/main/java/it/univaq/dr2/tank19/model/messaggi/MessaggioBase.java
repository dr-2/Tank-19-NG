package it.univaq.dr2.tank19.model.messaggi;

import lombok.Data;

@Data
public class MessaggioBase {
    private TipoMessaggio tipoMessaggio;
    //private String sender;
    private Long idPartita;
}
