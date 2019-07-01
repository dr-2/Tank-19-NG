package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RilevatoreCollisioni implements Collisione {
    private final FactoryRegoleCollisione factoryRegoleCollisione = FactoryRegoleCollisioneImpl.getInstance();

    @Override
    public Boolean isColliding(OggettoDiGioco oggettoMosso) {
        Polygon polygon = oggettoMosso.getPolygon();
        Partita partita = oggettoMosso.getPartita();

        final OggettoDiGioco[] altroOggetto = {null};
        partita.getOggettiPartita().iterator().forEachRemaining(oggettoDiGioco -> {
            Polygon poly2 = oggettoDiGioco.getPolygon();
            boolean stessoOggetto = oggettoMosso.getId().equals(oggettoDiGioco.getId()) && oggettoMosso.getTipo() == oggettoDiGioco.getTipo();

            if (!stessoOggetto && polygon.getBounds().intersects(poly2.getBounds())) {
                // Collisione trovata. Riportiamo il risultato fuori dalla lambda
                altroOggetto[0] = oggettoDiGioco;
            }
        });

        Boolean collisione = altroOggetto[0] != null;

        if (collisione) {
            RegolaCollisione regola = factoryRegoleCollisione.getRegolaPer(oggettoMosso.getTipo(), altroOggetto[0].getTipo());
            regola.applicaEffetto(oggettoMosso, altroOggetto[0]);
        }
        return collisione;
    }


}
