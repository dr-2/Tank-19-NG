package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Posizione;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.oggettigioco.OggettiDiGiocoFactory;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

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
            Posizione posizione = new Posizione();
            posizione.setTipoOggettoPadre(TipoOggetto.CARRO_ARMATO);

            try {
                proiettile = (Proiettile) oggettiDiGiocoFactory.getProiettile();
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
            Direzione direzione = oggettoDiGioco.getDirezione();

            posizione.setPosY(oggettoDiGioco.getPosizione().getPosY() + 15);
            posizione.setPosX(oggettoDiGioco.getPosizione().getPosX() + 15);

            assert proiettile != null;
            proiettile.setDirezione(direzione);
            proiettile.setPosizione(posizione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);
        } else { // TODO: al momento un tank non può sparare più di un proiettile
        }
    }


}
