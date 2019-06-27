package it.univaq.dr2.tank19.service;

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

    public ServiceInviaAggiornamentiStato(ServiceTank serviceTank, SimpMessagingTemplate simpMessagingTemplate) {
        this.serviceTank = serviceTank;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedDelay = 1000 / 60)
    public void inviaAggiornamentiDiStato() {
//        Set<OggettoDiGioco> oggetti = new HashSet<>();
        serviceTank.findAll().iterator().forEachRemaining(oggetto -> {
            String URLMessaggiPartita = "/partite/" + oggetto.getPartita().getId() + "/stato";
            String direzione;
            try {
                direzione = oggetto.getDirezione().toString();
            } catch (NullPointerException e) {
                direzione = "null";
            }

            MessaggioDiAggiornamentoStato messaggio = new MessaggioDiAggiornamentoStato();
            messaggio.setIdOggetto(oggetto.getId());
            messaggio.setPosx(oggetto.getPosizione().getPosX());
            messaggio.setPosy(oggetto.getPosizione().getPosY());
            messaggio.setDirezione(direzione);
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggio);
        });
    }
}
