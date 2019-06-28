package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.service.ServiceProiettili;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlo Centofanti
 * @created 23/06/2019
 */
@Component
public class ControllerGRASPFacade {
    private final ServiceTank serviceTank;
    private final ServiceProiettili serviceProiettili;

    public ControllerGRASPFacade(ServiceTank serviceTank, ServiceProiettili serviceProiettili) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
    }

    public void eseguiComandi(Long idOggetto, Direzione direzione, Boolean fuoco) {
        Boolean collisione = false;
        OggettoDiGioco currentOggettoDiGioco = serviceTank.findById(idOggetto);
        if (direzione != null) {
            currentOggettoDiGioco.setDirezione(direzione);
            currentOggettoDiGioco.setComandoMovimento();
            currentOggettoDiGioco.eseguiComando();
        }
        if (fuoco) {
            currentOggettoDiGioco.setComandoFuoco();
            currentOggettoDiGioco.eseguiComando();
        }
        // se il tank ha un proiettile, devo muoverlo
        if (currentOggettoDiGioco.getProiettile() != null) {
            currentOggettoDiGioco.getProiettile().setComandoMovimento();
            currentOggettoDiGioco.getProiettile().eseguiComando();
        }
        if (!collisione) {
            serviceTank.save((Tank) currentOggettoDiGioco);
        } else System.out.println("COLLISIONE RILEVATA!!");
    }

    public Boolean verificaCollisione(OggettoDiGioco oggettoDiGioco) {
        Set<OggettoDiGioco> oggetti = new HashSet<>();
        serviceProiettili.findAll().iterator().forEachRemaining(oggetti::add);
        serviceTank.findAll().iterator().forEachRemaining(oggetti::add);

        while (oggetti.iterator().hasNext()) {
            OggettoDiGioco oggetto = oggetti.iterator().next();
            if (oggetto.collidoCon(oggettoDiGioco) != null) {
                return true;
            }
        }
        return false;
    }

}
