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
    private final ServiceOggettoDiGioco serviceOggettoDiGioco;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ServiceInviaAggiornamentiStato(ServiceOggettoDiGioco serviceOggettoDiGioco, SimpMessagingTemplate simpMessagingTemplate) {
        this.serviceOggettoDiGioco = serviceOggettoDiGioco;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedDelay = 1000 / 60)
    public void inviaAggiornamentiDiStato() {
//        Set<OggettoDiGioco> oggetti = new HashSet<>();
        serviceOggettoDiGioco.findAll().iterator().forEachRemaining(oggetto -> {
            String URLMessaggiPartita = "/partite/" + oggetto.getPartita().getId() + "/stato";
            MessaggioDiAggiornamentoStato messaggio = new MessaggioDiAggiornamentoStato();
            messaggio.setIdOggetto(oggetto.getId());
            messaggio.setPosx(oggetto.getPosizione().getPosX());
            messaggio.setPosy(oggetto.getPosizione().getPosY());
            simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggio);
        });
    }
}
