package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.messaggi.MessaggioBase;
import it.univaq.dr2.tank19.model.messaggi.MessaggioComando;
import it.univaq.dr2.tank19.model.messaggi.MessaggioDiStato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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

    @MessageMapping("/partite/{idpartita}/.inviaComando")
    public MessaggioBase inviaMessaggioComando(@Payload MessaggioComando messaggio, @DestinationVariable("idpartita") String idPartita) {
        String URLMessaggiPartita = "/partite/" + idPartita + "/stato";

        log.debug("ho ricevuto da /partita.inviaComando:content, mittente, tipo, idpartita: " + messaggio.getContent()
                + ", " + messaggio.getSender() + ", " + messaggio.getTipoMessaggio() + ", " + idPartita + messaggio.toString());

        MessaggioDiStato messaggioStatoOggetto = new MessaggioDiStato();
//        messaggioStatoOggetto.setIdOggetto(1L);
//        messaggioStatoOggetto.setPosx(200);
//        messaggioStatoOggetto.setPosy(100);

        simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggioStatoOggetto);

        // System.out.println();
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.connessioneGiocatore")
    public MessaggioBase connessioneGiocatore(@Payload MessaggioBase messaggio, @DestinationVariable("idpartita") String idPartita) {
        log.debug("ho ricevuto da /partita.connessioneGiocatore content, sender, tipo, idpartita: "
                + messaggio.getContent() + ", " + messaggio.getSender() + ", " + messaggio.getTipoMessaggio()
                + ", " + idPartita);

        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.inviaMessaggioChat")
    public MessaggioBase inviaMessaggioChat(@Payload MessaggioBase messaggio) {
        //TODO: implementare
        return messaggio;
    }

}
