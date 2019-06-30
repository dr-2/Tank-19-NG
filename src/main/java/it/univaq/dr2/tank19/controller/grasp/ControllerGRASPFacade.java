package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.service.ServiceProiettili;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 23/06/2019
 */
@Component
public class ControllerGRASPFacade {
    private final ServiceTank serviceTank;
    private final ServiceProiettili serviceProiettili;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RilevatoreCollisioni rilevatoreCollisioni;

    public ControllerGRASPFacade(ServiceTank serviceTank, ServiceProiettili serviceProiettili, SimpMessagingTemplate simpMessagingTemplate, RilevatoreCollisioni rilevatoreCollisioni) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.rilevatoreCollisioni = rilevatoreCollisioni;
    }

    public void eseguiComandi(Long idOggetto, Direzione direzione, Boolean fuoco) {
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
        if (!rilevatoreCollisioni.generaCollisione(currentOggettoDiGioco)) {
            serviceTank.save((Tank) currentOggettoDiGioco);
        } else {
            System.out.println("COLLISIONE RILEVATA!!");
            // memento per annullare la mossa
            // ruotare solamente il carro
            // salvare
        }
    }



    @Scheduled(fixedDelay = 1000 / 60)
    public void gameTick() {

        inviaAggiornamentiDiStato();
    }

    private void inviaAggiornamentiDiStato() {
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
            aggiornamentoTank.setPosx(tank.getPosX());
            aggiornamentoTank.setPosy(tank.getPosY());
            aggiornamentoTank.setDirezione(direzione);
            aggiornamentoTank.setTipoOggetto(TipoOggetto.CARRO_ARMATO);

            if (tank.getProiettile() != null) {
                Proiettile proiettile = tank.getProiettile();
                aggiornamentoProiettile.setIdOggetto(proiettile.getId());
                aggiornamentoProiettile.setPosx(proiettile.getPosX());
                aggiornamentoProiettile.setPosy(proiettile.getPosY());
                aggiornamentoProiettile.setDirezione(proiettile.getDirezione().toString());
                aggiornamentoProiettile.setTipoOggetto(TipoOggetto.PROIETTILE);
            }
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoTank);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoProiettile);
        });
    }

}
