package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.service.ServiceProiettili;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ControllerGRASPFacade(ServiceTank serviceTank, ServiceProiettili serviceProiettili, SimpMessagingTemplate simpMessagingTemplate) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
        this.simpMessagingTemplate = simpMessagingTemplate;
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

    @Scheduled(fixedDelay = 1000 / 60)
    public void inviaAggiornamentiDiStato() {
        serviceTank.findAll().iterator().forEachRemaining(tank -> {
            String URLMessaggiPartita = "/partite/" + tank.getPartita().getId() + "/stato";
            String direzione;
            try {
                direzione = tank.getDirezione().toString();
            } catch (NullPointerException e) {
                direzione = "null";
            }

            MessaggioDiAggiornamentoStato aggiornamentoTank = new MessaggioDiAggiornamentoStato();
            MessaggioDiAggiornamentoStato aggiornamentoProiettile = new MessaggioDiAggiornamentoStato();

            aggiornamentoTank.setIdOggetto(tank.getId());
            aggiornamentoTank.setPosx(tank.getPosizione().getPosX());
            aggiornamentoTank.setPosy(tank.getPosizione().getPosY());
            aggiornamentoTank.setDirezione(direzione);
            aggiornamentoTank.setTipoOggetto(TipoOggetto.CARRO_ARMATO);

            if (tank.getProiettile() != null) {
                Proiettile proiettile = tank.getProiettile();
                aggiornamentoProiettile.setIdOggetto(proiettile.getId());
                aggiornamentoProiettile.setPosx(proiettile.getPosizione().getPosX());
                aggiornamentoProiettile.setPosy(proiettile.getPosizione().getPosY());
                aggiornamentoProiettile.setDirezione(proiettile.getDirezione().toString());
                aggiornamentoProiettile.setTipoOggetto(TipoOggetto.PROIETTILE);
            }
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoTank);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoProiettile);
        });
    }

}
