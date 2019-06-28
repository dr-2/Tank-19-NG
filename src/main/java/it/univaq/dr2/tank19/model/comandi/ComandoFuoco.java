package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Posizione;
import it.univaq.dr2.tank19.model.gioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.gioco.Proiettile;
import it.univaq.dr2.tank19.model.gioco.Tank;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        if (oggettoDiGioco.getProiettile() == null) {
            Proiettile proiettile = new Proiettile();
            Direzione direzione = oggettoDiGioco.getDirezione();
            Posizione posizione = new Posizione();

            posizione.setPosY(oggettoDiGioco.getPosizione().getPosY());
            posizione.setPosX(oggettoDiGioco.getPosizione().getPosX());

            proiettile.setDirezione(direzione);
            proiettile.setPosizione(posizione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);
        } else {
        }
    }


}
