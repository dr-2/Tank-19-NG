package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.ComandoFuoco;
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

    public void eseguiComandi(Long idOggetto, Direzione direzione, Boolean fuoco) {
        Tank currentOggettoDiGioco = serviceTank.findById(idOggetto);
        if (direzione != null) {
            currentOggettoDiGioco.setDirezione(direzione);
            currentOggettoDiGioco.setComando(new ComandoMossa());
            currentOggettoDiGioco.eseguiComando();
        }
        if (fuoco) {
            currentOggettoDiGioco.setComando(new ComandoFuoco());
            currentOggettoDiGioco.eseguiComando();
        }
        serviceTank.save(currentOggettoDiGioco);
    }


}
