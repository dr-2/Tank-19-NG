package it.univaq.dr2.tank19.controller.mvc;

import it.univaq.dr2.tank19.controller.grasp.ControllerGRASPFacade;
import it.univaq.dr2.tank19.model.messaggi.MessaggioBase;
import it.univaq.dr2.tank19.model.messaggi.MessaggioComando;
import it.univaq.dr2.tank19.model.messaggi.MessaggioConnessione;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ControllerMessaggiPartita {
    private final ControllerGRASPFacade controllerGRASPFacade;

    public ControllerMessaggiPartita(ControllerGRASPFacade controllerGRASPFacade) {
        this.controllerGRASPFacade = controllerGRASPFacade;
    }

    @MessageMapping("/partite/{idpartita}/.inviaComando")
    public MessaggioBase inviaMessaggioComando(@Payload MessaggioComando messaggio, @DestinationVariable("idpartita") String idPartita) {
        String URLMessaggiPartita = "/partite/" + idPartita + "/stato";

        controllerGRASPFacade.eseguiComandi(messaggio.getIdOggetto(), messaggio.getDirezione(), messaggio.getFuoco());


        //servicePartita.doMossa(messaggio);
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.connessioneGiocatore")
    public MessaggioBase connessioneGiocatore(@Payload MessaggioConnessione messaggio, @DestinationVariable("idpartita") String idPartita) {
        // Todo: implementare la logica per la connessione del giocatore alla partita?
        return messaggio;
    }

    @MessageMapping("/partite/{idpartita}/.inviaMessaggioChat")
    public MessaggioBase inviaMessaggioChat(@Payload MessaggioBase messaggio) {
        //TODO: implementare
        return messaggio;
    }

}
