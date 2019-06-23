package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.ComandoMossa;
import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.OggettoDiGioco;
import it.univaq.dr2.tank19.service.ServiceOggettoDiGioco;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 23/06/2019
 */
@Component
public class ControllerGRASP {
    private final ServiceOggettoDiGioco serviceOggettoDiGioco;

    public ControllerGRASP(ServiceOggettoDiGioco serviceOggettoDiGioco) {
        this.serviceOggettoDiGioco = serviceOggettoDiGioco;
    }

    public void muovi(Long idOggetto, Direzione direzione) {
        if (direzione != null) {
            OggettoDiGioco currentOggettoDiGioco = serviceOggettoDiGioco.findById(idOggetto);
            currentOggettoDiGioco.setComando(new ComandoMossa());
            currentOggettoDiGioco.eseguiComando(direzione);
            serviceOggettoDiGioco.save(currentOggettoDiGioco);
        }
    }
}
