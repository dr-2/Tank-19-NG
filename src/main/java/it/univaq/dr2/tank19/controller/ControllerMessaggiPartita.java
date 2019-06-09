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

import java.util.Random;

@Slf4j
@Controller
public class ControllerMessaggiPartita {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ServicePartita servicePartita;

    public ControllerMessaggiPartita(SimpMessagingTemplate simpMessagingTemplate, ServicePartita servicePartita) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.servicePartita = servicePartita;
    }

    @MessageMapping("/partite/{idpartita}/.inviaComando")
    public MessaggioBase inviaMessaggioComando(@Payload MessaggioComando messaggio, @DestinationVariable("idpartita") String idPartita) {
        String URLMessaggiPartita = "/partite/" + idPartita + "/stato";

        Random random = new Random();

        log.debug("ho ricevuto da /partita.inviaComando:content, mittente, tipo, idpartita: " + messaggio.getContent()
                + ", " + messaggio.getSender() + ", " + messaggio.getTipoMessaggio() + ", " + idPartita + messaggio.toString());

        servicePartita.aggiornaStato(messaggio);


//        MessaggioDiAggiornamentoStato messaggioDiAggiornamentoStato = new MessaggioDiAggiornamentoStato();
//        messaggioDiAggiornamentoStato.setIdOggetto(1L);
//        messaggioDiAggiornamentoStato.setPosx(random.nextInt(800));
//        messaggioDiAggiornamentoStato.setPosy(random.nextInt(600));
//
//        simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggioDiAggiornamentoStato);
//
//        messaggioDiAggiornamentoStato.setIdOggetto(2L);
//        messaggioDiAggiornamentoStato.setPosx(random.nextInt(800));
//        messaggioDiAggiornamentoStato.setPosy(random.nextInt(600));
//
//        simpMessagingTemplate.convertAndSend(URLMessaggiPartita, messaggioDiAggiornamentoStato);


        // System.out.println();
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.connessioneGiocatore")
    public MessaggioBase connessioneGiocatore(@Payload MessaggioBase messaggio, @DestinationVariable("idpartita") String idPartita) {
        log.debug("ho ricevuto da /partita.connessioneGiocatore content, sender, tipo, idpartita: "
                + messaggio.getContent() + ", " + messaggio.getSender() + ", " + messaggio.getTipoMessaggio()
                + ", " + idPartita);

//        OggettoDiGioco tank = OggettoDiGioco.builder().partita(servicePartita.findById(1L)).posX(100).posY(100).build();
//        Partita p = servicePartita.findById(1L);
//        p.aggiungiOggettoDiGioco(tank);
//        servicePartita.save(p);

        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.inviaMessaggioChat")
    public MessaggioBase inviaMessaggioChat(@Payload MessaggioBase messaggio) {
        //TODO: implementare
        return messaggio;
    }

}
