package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.messaggi.MessaggioBase;
import it.univaq.dr2.tank19.model.messaggi.MessaggioComando;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ControllerMessaggiPartita {
    private SimpMessagingTemplate simpMessagingTemplate;

    public ControllerMessaggiPartita(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/partita.inviaComando")
    public MessaggioBase inviaMessaggioComando(@Payload MessaggioComando messaggio) {
        log.debug("ho ricevuto da /partita.inviaComando:content, mittente, tipo: " + messaggio.getContent() + ", " + messaggio.getSender() + " " + messaggio.getTipoMessaggio());

        simpMessagingTemplate.convertAndSend("/partita/public", messaggio);

        // System.out.println();
        return messaggio;
    }

    @MessageMapping("/partita.connessioneGiocatore")
    public MessaggioBase connessioneGiocatore(@Payload MessaggioBase messaggio) {
        log.debug("ho ricevuto da /partita.connessioneGiocatore content, sender, tipo: " + messaggio.getContent() + ", " + messaggio.getSender() + ", " + messaggio.getTipoMessaggio());
        return messaggio;
    }

    @MessageMapping("/partita.inviaMessaggioChat")
    public MessaggioBase inviaMessaggioChat(@Payload MessaggioBase messaggio) {
        //TODO: implementare
        return messaggio;
    }

}
