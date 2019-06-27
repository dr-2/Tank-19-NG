package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.TipoOggetto;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiAggiornamentoStato;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Carlo Centofanti
 * @created 09/06/2019
 */
@Service
public class ServiceInviaAggiornamentiStato {
    private final ServiceTank serviceTank;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ServiceProiettili serviceProiettili;

    public ServiceInviaAggiornamentiStato(ServiceTank serviceTank, SimpMessagingTemplate simpMessagingTemplate, ServiceProiettili serviceProiettili) {
        this.serviceTank = serviceTank;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.serviceProiettili = serviceProiettili;
    }

    @Scheduled(fixedDelay = 1000 / 60)
    public void inviaAggiornamentiDiStato() {
//        Set<OggettoDiGioco> oggetti = new HashSet<>();
        serviceTank.findAll().iterator().forEachRemaining(tank -> {
            String URLMessaggiPartita = "/partite/" + tank.getPartita().getId() + "/stato";
            String direzione;
            try {
                direzione = tank.getDirezione().toString();
            } catch (NullPointerException e) {
                direzione = "null";
            }

            MessaggioDiAggiornamentoStato messaggio = new MessaggioDiAggiornamentoStato();
            messaggio.setIdOggetto(tank.getId());
            messaggio.setPosx(tank.getPosizione().getPosX());
            messaggio.setPosy(tank.getPosizione().getPosY());
            messaggio.setDirezione(direzione);
            messaggio.setTipoOggetto(TipoOggetto.CARRO_ARMATO);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggio);
        });

        serviceProiettili.findAll().iterator().forEachRemaining(proiettile -> {
            String URLMessaggiPartita = "/partite/" + proiettile.getTank().getPartita().getId() + "/stato";
            String direzione;
            try {
                direzione = proiettile.getDirezione().toString();
            } catch (NullPointerException e) {
                direzione = "null";
            }

            MessaggioDiAggiornamentoStato messaggio = new MessaggioDiAggiornamentoStato();
            messaggio.setIdOggetto(proiettile.getId());
            messaggio.setPosx(proiettile.getPosizione().getPosX());
            messaggio.setPosy(proiettile.getPosizione().getPosY());
            messaggio.setDirezione(direzione);
            messaggio.setTipoOggetto(TipoOggetto.PROIETTILE);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggio);
        });


    }
}
