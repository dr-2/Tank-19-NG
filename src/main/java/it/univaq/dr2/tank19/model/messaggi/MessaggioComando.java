package it.univaq.dr2.tank19.model.messaggi;

import lombok.Data;

@Data
public class MessaggioComando extends MessaggioBase {
    Boolean nord;
    Boolean sud;
    Boolean est;
    Boolean ovest;
    Boolean fuoco;
}
