package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;

@Slf4j
@Component
public class RilevatoreCollisioneImpl implements RilevatoreCollisione {
    private final FactoryRegoleCollisione factoryRegoleCollisione = FactoryRegoleCollisioneImpl.getInstance();

    private OggettoDiGioco oggettoMosso;
    private OggettoDiGioco oggettoCheSubisceCollisione;
    Boolean collisione = false;

    @Override
    public Boolean isColliding(OggettoDiGioco oggettoCheSiMuove) {
        this.oggettoMosso = oggettoCheSiMuove;
        Polygon polygon = oggettoMosso.getPolygon();
        Partita partita = oggettoMosso.getPartita();

        partita.getOggettiPartita().iterator().forEachRemaining(oggettoDiGioco -> {
            Polygon poly2 = oggettoDiGioco.getPolygon();
            boolean stessoOggetto = oggettoMosso.getId().equals(oggettoDiGioco.getId()) && oggettoMosso.getTipo() == oggettoDiGioco.getTipo();

            if (!stessoOggetto && polygon.getBounds().intersects(poly2.getBounds())) {
                // RilevatoreCollisione trovata. Riportiamo il risultato fuori dalla lambda
                this.oggettoCheSubisceCollisione = oggettoDiGioco;
                collisione = true;
            }
        });

        return collisione;
    }

    @Override
    public void applicaCollisione() {

        log.info("RilevatoreCollisione avvenuta tra: " + oggettoMosso.getTipo() + " id=" + oggettoMosso.getId() + " |e| " + oggettoCheSubisceCollisione.getTipo() + " id=" + oggettoCheSubisceCollisione.getId());
        RegolaCollisione regola = factoryRegoleCollisione.getRegolaPer(oggettoMosso.getTipo(), oggettoCheSubisceCollisione.getTipo());
        regola.applicaEffetto(oggettoMosso, oggettoCheSubisceCollisione);
    }
}
