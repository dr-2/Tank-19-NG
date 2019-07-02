package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.comandi.FactoryComandi;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.service.ServiceMuretti;
import it.univaq.dr2.tank19.service.ServicePartita;
import it.univaq.dr2.tank19.service.ServiceProiettili;
import it.univaq.dr2.tank19.service.ServiceTank;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ControllerGRASPFacade {
    private final ServiceTank serviceTank;
    private final ServiceProiettili serviceProiettili;
    private final ServiceMuretti serviceMuretti;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RilevatoreCollisioni rilevatoreCollisioni;
    private final FactoryComandi factoryComandi;
    private final ServicePartita servicePartita;

    public ControllerGRASPFacade(ServiceTank serviceTank, ServiceProiettili serviceProiettili,
                                 ServiceMuretti serviceMuretti, SimpMessagingTemplate simpMessagingTemplate,
                                 RilevatoreCollisioni rilevatoreCollisioni, FactoryComandi factoryComandi,
                                 ServicePartita servicePartita) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
        this.serviceMuretti = serviceMuretti;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.rilevatoreCollisioni = rilevatoreCollisioni;
        this.factoryComandi = factoryComandi;
        this.servicePartita = servicePartita;
    }

    public void eseguiComandi(Long idOggetto, Direzione direzione, Boolean fuoco) {
        OggettoDiGioco currentOggettoDiGioco = serviceTank.findById(idOggetto);
        if (direzione != null) {
            currentOggettoDiGioco.setComando(factoryComandi.getComandoMuoviA(direzione));
            currentOggettoDiGioco.eseguiComando();
        }
        if (fuoco) {
            currentOggettoDiGioco.setComandoFuoco();
            currentOggettoDiGioco.eseguiComando();
        }

        //la collisione non è responsabilità di questo controller. spostarla in un implementazione di comando
        serviceTank.save((Tank) currentOggettoDiGioco);

    }



    @Scheduled(fixedDelay = 1000 / 60)
    public void gameTick() {
        rimuoviProiettiliMorti();
        //rimuoviMurettiMorti();
        muoviProiettili();
        inviaAggiornamentiDiStato();
    }

    private void rimuoviProiettiliMorti() {
        serviceProiettili.findAll().iterator().forEachRemaining(proiettile -> {
            if (proiettile.getVita() < 1) {
                Tank t = serviceTank.findById(proiettile.getTank().getId());
                t.setProiettile(null);
                serviceTank.save(t);
                serviceProiettili.deleteById(proiettile.getId());
                System.out.println("Proiettile morto rimosso"); // Test
            }
        });

    }

    private void rimuoviMurettiMorti() {
        serviceMuretti.findAll().iterator().forEachRemaining(muretto -> {
            if (muretto.getVita() < 1) {
                serviceMuretti.deleteById(muretto.getId());
                System.out.println("Muretto morto rimosso"); // Test
            }
        });

    }

    private void muoviProiettili() {
        serviceProiettili.findAll().iterator().forEachRemaining(proiettile -> {
            proiettile.setComando(factoryComandi.getComandoMuoviA(proiettile.getDirezione()));
            proiettile.eseguiComando();
            serviceProiettili.save(proiettile);
        });
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
