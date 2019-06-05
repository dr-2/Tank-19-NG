package it.univaq.dr2.tank19.model;

import lombok.Data;

@Data
public class Messaggio {
    private TipoMessaggio tipoMessaggio;
    private String content;
    private String sender;


}
