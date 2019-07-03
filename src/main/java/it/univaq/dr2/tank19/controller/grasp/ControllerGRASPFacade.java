package it.univaq.dr2.tank19.controller.grasp;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioneImpl;
import it.univaq.dr2.tank19.model.comandi.FactoryComandi;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import it.univaq.dr2.tank19.model.messaggi.TipoMessaggio;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.repository.RepositoryMuretto;
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
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RilevatoreCollisioneImpl rilevatoreCollisioni;
    private final FactoryComandi factoryComandi;
    private final ServicePartita servicePartita;
    private final RepositoryMuretto repositoryMuretto;

    public ControllerGRASPFacade(ServiceTank serviceTank, ServiceProiettili serviceProiettili, SimpMessagingTemplate simpMessagingTemplate, RilevatoreCollisioneImpl rilevatoreCollisioni, FactoryComandi factoryComandi, ServicePartita servicePartita, RepositoryMuretto repositoryMuretto) {
        this.serviceTank = serviceTank;
        this.serviceProiettili = serviceProiettili;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.rilevatoreCollisioni = rilevatoreCollisioni;
        this.factoryComandi = factoryComandi;
        this.servicePartita = servicePartita;
        this.repositoryMuretto = repositoryMuretto;
    }

    public synchronized void eseguiComandi(Long idOggetto, Direzione direzione, Boolean fuoco) {
        OggettoDiGioco currentOggettoDiGioco = serviceTank.findById(idOggetto);
        if (direzione != null) {
            currentOggettoDiGioco.setComando(factoryComandi.getComandoMuoviA(direzione));
            currentOggettoDiGioco.eseguiComando();
        }
        if (fuoco) {
            currentOggettoDiGioco.setComandoFuoco();
            currentOggettoDiGioco.eseguiComando();
        }
        serviceTank.save((Tank) currentOggettoDiGioco);
    }



    @Scheduled(fixedDelay = 1000 / 60)
    public synchronized void gameTick() {
        muoviProiettili();
        rimuoviProiettiliMorti();
        rimuoviMurettiMorti();
        inviaAggiornamentiDiStato();
    }

    private void rimuoviMurettiMorti() {
        servicePartita.findAll().iterator().forEachRemaining(partita -> {
            partita.getMuretti().iterator().forEachRemaining(muretto -> {
                if (muretto.getVita() < 1) {
                    inviaRimozioneOggetto(muretto);
                    partita.getMuretti().remove(muretto);
                    servicePartita.save(partita);
                }
            });
        });
    }

    private void rimuoviProiettiliMorti() {
        serviceProiettili.findAll().iterator().forEachRemaining(proiettile -> {
            if (proiettile.getVita() < 1) {
                inviaRimozioneOggetto(proiettile);
                Tank t = serviceTank.findById(proiettile.getTank().getId());
                t.setProiettile(null);
                serviceTank.save(t);
            }
        });

    }

    private void inviaRimozioneOggetto(OggettoDiGioco oggettoDiGioco) {
        String URLMessaggiPartita = "/partite/" + oggettoDiGioco.getPartita().getId() + "/stato";
        MessaggioDiAggiornamentoStato messaggioRimozioneOggetto = new MessaggioDiAggiornamentoStato();

        messaggioRimozioneOggetto.setIdOggetto(oggettoDiGioco.getId());
        messaggioRimozioneOggetto.setTipoMessaggio(TipoMessaggio.RIMUOVI_OGGETTO);
        messaggioRimozioneOggetto.setTipoOggetto(oggettoDiGioco.getTipo());

        simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggioRimozioneOggetto);
    }

    private void muoviProiettili() {
        servicePartita.findAll().iterator().forEachRemaining(partita -> {
            partita.getTanks().iterator().forEachRemaining(tank -> {
                Proiettile proiettile = tank.getProiettile();
                if (proiettile != null) {
                    proiettile.setComando(factoryComandi.getComandoMuoviA(proiettile.getDirezione()));
                    proiettile.eseguiComando();
                    //serviceTank.save(tank);
                }
            });
            servicePartita.save(partita);
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
            aggiornamentoTank.setTipoOggetto(tank.getTipo());

            if (tank.getProiettile() != null) {
                Proiettile proiettile = tank.getProiettile();
                aggiornamentoProiettile.setIdOggetto(proiettile.getId());
                aggiornamentoProiettile.setPosx(proiettile.getPosX());
                aggiornamentoProiettile.setPosy(proiettile.getPosY());
                aggiornamentoProiettile.setDirezione(proiettile.getDirezione().toString());
                aggiornamentoProiettile.setTipoOggetto(proiettile.getTipo());
            }
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoTank);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoProiettile);
        });
        servicePartita.findAll().iterator().forEachRemaining(partita -> {
            partita.getMuretti().iterator().forEachRemaining(muretto -> {
                String URLMessaggiPartita = "/partite/" + muretto.getPartita().getId() + "/stato";
                MessaggioDiAggiornamentoStato aggiornamentoMuretti = new MessaggioDiAggiornamentoStato();
                aggiornamentoMuretti.setIdOggetto(muretto.getId());
                aggiornamentoMuretti.setPosx(muretto.getPosX());
                aggiornamentoMuretti.setPosy(muretto.getPosY());
                aggiornamentoMuretti.setTipoOggetto(muretto.getTipo());

                simpMessagingTemplate.convertAndSend(URLMessaggiPartita, aggiornamentoMuretti);
            });
        });
    }

}
