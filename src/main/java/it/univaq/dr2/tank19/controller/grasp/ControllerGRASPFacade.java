package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.ComandoMossa;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.gioco.Tank;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 23/06/2019
 */
@Component
public class ControllerGRASPFacade {
    private final ServiceTank serviceTank;

    public ControllerGRASPFacade(ServiceTank serviceTank) {
        this.serviceTank = serviceTank;
    }

    public void muovi(Long idOggetto, Direzione direzione) {
        if (direzione != null) {
            Tank currentOggettoDiGioco = serviceTank.findById(idOggetto);
            currentOggettoDiGioco.setComando(new ComandoMossa());
            currentOggettoDiGioco.eseguiComando(direzione);
            serviceTank.save(currentOggettoDiGioco);
        }
    }
}
