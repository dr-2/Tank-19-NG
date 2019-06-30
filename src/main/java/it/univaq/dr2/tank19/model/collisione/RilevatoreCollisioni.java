package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RilevatoreCollisioni implements Collisione {


    @Override
    public Boolean isColliding(OggettoDiGioco oggettoMosso) {
        Polygon polygon = oggettoMosso.getPolygon();
        Partita partita = oggettoMosso.getPartita();

        final OggettoDiGioco[] altroOggetto = {null};
        partita.getOggettiPartita().iterator().forEachRemaining(oggettoDiGioco -> {
            Polygon poly2 = oggettoDiGioco.getPolygon();
            if (!(oggettoMosso.getId().equals(oggettoDiGioco.getId()) && oggettoMosso.getTipo() == oggettoDiGioco.getTipo()) && polygon.getBounds().intersects(poly2.getBounds())) {
                altroOggetto[0] = oggettoDiGioco;
                //System.out.println("TROVATO!!!");
            }
        });

        return altroOggetto[0] != null;
    }

}
