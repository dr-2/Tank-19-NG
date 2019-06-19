package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.messaggi.MessaggioBase;
import it.univaq.dr2.tank19.model.messaggi.MessaggioComando;
import it.univaq.dr2.tank19.service.ServicePartita;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ControllerMessaggiPartita {
    private final ServicePartita servicePartita;

    public ControllerMessaggiPartita(SimpMessagingTemplate simpMessagingTemplate, ServicePartita servicePartita) {
        this.servicePartita = servicePartita;
    }

    @MessageMapping("/partite/{idpartita}/.inviaComando")
    public MessaggioBase inviaMessaggioComando(@Payload MessaggioComando messaggio, @DestinationVariable("idpartita") String idPartita) {
        String URLMessaggiPartita = "/partite/" + idPartita + "/stato";


        servicePartita.eseguiComando(messaggio);
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.connessioneGiocatore")
    public MessaggioBase connessioneGiocatore(@Payload MessaggioBase messaggio, @DestinationVariable("idpartita") String idPartita) {
        // Todo: implementare la logica per la connessione del giocatore alla partita?
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.inviaMessaggioChat")
    public MessaggioBase inviaMessaggioChat(@Payload MessaggioBase messaggio) {
        //TODO: implementare
        return messaggio;
    }

}
