package it.univaq.dr2.tank19.model.messaggi;

import it.univaq.dr2.tank19.model.Direzione;
import lombok.Data;

@Data
public class MessaggioComando extends MessaggioBase {
    Long idOggetto;
    Boolean nord;
    Boolean sud;
    Boolean est;
    Boolean ovest;
    Boolean fuoco;

    //Regole di controllo derivate da REQ
    public Direzione getDirezione() {
        Direzione d = null;
        if (nord) {
            d = Direzione.NORD;
        } else if (sud) {
            d = Direzione.SUD;
        } else if (est) {
            d = Direzione.EST;
        } else if (ovest) {
            d = Direzione.OVEST;
        }
        return d;
    }
}
