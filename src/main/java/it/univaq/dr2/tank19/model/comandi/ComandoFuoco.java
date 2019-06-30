package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.Collisione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.oggettigioco.OggettiDiGiocoFactory;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.stereotype.Component;


@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        if (oggettoDiGioco.getProiettile() == null) {
            OggettiDiGiocoFactory oggettiDiGiocoFactory = OggettiDiGiocoFactory.getInstance();
            Proiettile proiettile = null;

            proiettile = (Proiettile) oggettiDiGiocoFactory.getProiettile();
            Direzione direzione = oggettoDiGioco.getDirezione();
            proiettile.setDirezione(direzione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);

            // Quando viene generato, il proiettile si trova in corrispondenza del centro del Tank generatore
            proiettile.setPosX((int)oggettoDiGioco.getPolygon().getBounds().getCenterX());
            proiettile.setPosY((int)oggettoDiGioco.getPolygon().getBounds().getCenterY());




        } else { // TODO: al momento un tank non può sparare più di un proiettile
        }
    }




}