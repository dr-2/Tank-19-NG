package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.oggettigioco.OggettiDiGiocoFactory;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
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
            OggettiDiGiocoFactory oggettiDiGiocoFactory = OggettiDiGiocoFactory.getInstance();
            Proiettile proiettile = null;

            proiettile = (Proiettile) oggettiDiGiocoFactory.getProiettile();
            Direzione direzione = oggettoDiGioco.getDirezione();

            assert proiettile != null;
            proiettile.setPosY(oggettoDiGioco.getPosY() + 15);
            proiettile.setPosX(oggettoDiGioco.getPosX() + 15);

            proiettile.setDirezione(direzione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);
        } else { // TODO: al momento un tank non può sparare più di un proiettile
        }
    }


}
