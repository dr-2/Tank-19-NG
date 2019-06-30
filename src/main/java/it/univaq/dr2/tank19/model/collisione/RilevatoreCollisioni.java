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

        System.out.println("L'oggetto mosso è: " + oggettoMosso.getId().toString());

        if(altroOggetto[0] != null){
            System.out.println("COLLIDE CON: " + altroOggetto[0].getId().toString()); // Test
            System.out.println("La vita dell'oggetto lanciato è: " + oggettoMosso.getVita().toString()); // Test
            System.out.println("La vita dell'oggetto colpito è: " + altroOggetto[0].getVita().toString()); // Test
            boom(altroOggetto[0], oggettoMosso);
            System.out.println("La nuova vita dell'oggetto lanciato è: " + oggettoMosso.getVita().toString()); // Test
            System.out.println("La nuova vita dell'oggetto colpito è: " + altroOggetto[0].getVita().toString()); // Test

        }
        return altroOggetto[0] != null;
    }

    private void boom(OggettoDiGioco ogg1, OggettoDiGioco ogg2){
        if(ogg1.getTipo().toString() == "PROIETTILE" || ogg2.getTipo().toString() == "PROIETTILE"){
            System.out.println("BOOM!"); // Test
            ogg1.scalaVita(ogg2.getDanno());
            ogg2.scalaVita(ogg1.getDanno());
        }
    }

}
