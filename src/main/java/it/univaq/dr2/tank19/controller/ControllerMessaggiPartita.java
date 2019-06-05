package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.Messaggio;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerMessaggiPartita {
    private SimpMessagingTemplate simpMessagingTemplate;

    public ControllerMessaggiPartita(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/partita.inviaComando")
    public Messaggio inviaMessaggioComando(@Payload Messaggio messaggio) {
        System.out.println("ho ricevuto da /partita.inviaComando: " + messaggio.getContent());
        System.out.println("mittente e tipo: " + messaggio.getSender() + " " + messaggio.getTipoMessaggio());

        return messaggio;
    }

    @MessageMapping("/partita.connessioneGiocatore")
    public Messaggio connessioneGiocatore(@Payload Messaggio messaggio) {
        System.out.println("ho ricevuto da /partita.connessioneGiocatore: " + messaggio.getContent());
        System.out.println("mittente e tipo: " + messaggio.getSender() + " " + messaggio.getTipoMessaggio());

        return messaggio;
    }
}
