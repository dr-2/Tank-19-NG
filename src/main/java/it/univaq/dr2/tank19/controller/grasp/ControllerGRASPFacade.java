package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.comandi.FactoryComandi;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import it.univaq.dr2.tank19.model.oggettigioco.Muretto;
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

import java.util.Set;


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



    @Scheduled(fixedDelay = 100 / 60)
    public void gameTick() {
        rimuoviProiettiliMorti();
        rimuoviMurettiMorti();
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
        Set<OggettoDiGioco> tanks = (Set<OggettoDiGioco>)(Set<?>) serviceTank.findAll();
        Set<OggettoDiGioco> muretti = (Set<OggettoDiGioco>)(Set<?>) serviceMuretti.findAll();
        Set<OggettoDiGioco> proiettili = (Set<OggettoDiGioco>)(Set<?>) serviceProiettili.findAll();

        Set<OggettoDiGioco> oggettoDiGioco = tanks;
        oggettoDiGioco.addAll(muretti);
        oggettoDiGioco.addAll(proiettili);

        oggettoDiGioco.iterator().forEachRemaining(oggetto -> {
            String URLMessaggiPartita = "/partite/" + oggetto.getPartita().getId() + "/stato";
            String direzione;
            try {
                direzione = oggetto.getDirezione().toString();
            } catch (NullPointerException e) {
                direzione = "null";
            }

            MessaggioDiAggiornamentoStato aggiornamentoOggetto = new MessaggioDiAggiornamentoStato();

            aggiornamentoOggetto.setIdOggetto(oggetto.getId());
            aggiornamentoOggetto.setPosx(oggetto.getPosX());
            aggiornamentoOggetto.setPosy(oggetto.getPosY());
            aggiornamentoOggetto.setDirezione(direzione);
            aggiornamentoOggetto.setTipoOggetto(oggetto.getTipo());


            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoOggetto);

        });
    }


}
