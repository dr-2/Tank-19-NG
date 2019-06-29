package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.service.ServicePartita;
import it.univaq.dr2.tank19.service.ServiceProiettili;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class RilevatoreCollisioni implements Collisione {

    private final ServiceTank serviceTank;
    private final ServiceProiettili serviceProiettili;

    private final ServicePartita servicePartita;

    public RilevatoreCollisioni(ServiceTank serviceTank, ServiceProiettili serviceProiettili, ServicePartita servicePartita) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
        this.servicePartita = servicePartita;
    }

    @Override
    public OggettoDiGioco staCollidendoCon(OggettoDiGioco oggettoMosso) {
        Polygon polygon = getPolygonFromOggettoDiGioco(oggettoMosso);
        Set<OggettoDiGioco> oggettiDiGioco = new HashSet<>();
        serviceTank.findAll().iterator().forEachRemaining(oggettiDiGioco::add);
        serviceProiettili.findAll().iterator().forEachRemaining(oggettiDiGioco::add);
        final OggettoDiGioco[] altroOggetto = {null};

        oggettiDiGioco.iterator().forEachRemaining(oggettoDiGioco -> {
            Polygon poly2 = getPolygonFromOggettoDiGioco(oggettoDiGioco);
            if (!(oggettoMosso.getId().equals(oggettoDiGioco.getId()) && oggettoMosso.getTipo() == oggettoDiGioco.getTipo()) && polygon.getBounds().intersects(poly2.getBounds())) {
                altroOggetto[0] = oggettoDiGioco;
                System.out.println("TROVATO!!!");
            }
        });
        return altroOggetto[0];
    }

    public Polygon getPolygonFromOggettoDiGioco(OggettoDiGioco oggettoDiGioco) {
        int xPoly[] = {oggettoDiGioco.getPosX(), oggettoDiGioco.getXMax()};
        int yPoly[] = {oggettoDiGioco.getPosY(), oggettoDiGioco.getYMax()};
        return new Polygon(xPoly, yPoly, xPoly.length);
    }
}
