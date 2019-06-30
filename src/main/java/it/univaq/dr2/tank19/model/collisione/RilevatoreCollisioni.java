package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RilevatoreCollisioni implements Collisione {


    @Override
    public Boolean isColliding(OggettoDiGioco oggettoMosso) {
        Polygon polygon = oggettoMosso.getPolygon();
        Partita partita = oggettoMosso.getPartita();
        Integer danno = oggettoMosso.getDanno(); // Danno inflitto dall'oggetto mosso all'altro oggetto coinvolto nella collisione

        final OggettoDiGioco[] altroOggetto = {null};
        partita.getOggettiPartita().iterator().forEachRemaining(oggettoDiGioco -> {
            Polygon poly2 = oggettoDiGioco.getPolygon();
            if (!(oggettoMosso.getId().equals(oggettoDiGioco.getId()) && oggettoMosso.getTipo() == oggettoDiGioco.getTipo()) && polygon.getBounds().intersects(poly2.getBounds())) {
                altroOggetto[0] = oggettoDiGioco;
            }
            else{
                System.out.println("Non collide con: " + oggettoDiGioco.getId().toString()); // Test
            }
        });


        // Da questo test emerge che viene rilevata una auto-collisione!!!
        if(altroOggetto[0] != null){
            System.out.println("L'oggetto " + oggettoMosso.getId().toString() + " COLLIDE CON: " + altroOggetto[0].getId().toString());
            System.out.println("La vita dell'oggetto lanciato è: " + oggettoMosso.getVita().toString());
            System.out.println("La vita dell'oggetto colpito è: " + altroOggetto[0].getVita().toString());
            final Integer contraccolpo = altroOggetto[0].getDanno();
            altroOggetto[0].scalaVita(danno);
            oggettoMosso.scalaVita(contraccolpo);
            System.out.println("La nuova vita dell'oggetto lanciato è: " + oggettoMosso.getVita().toString());
            System.out.println("La nuova  vita dell'oggetto colpito è: " + altroOggetto[0].getVita().toString());
        }

        return altroOggetto[0] != null;
    }

}
