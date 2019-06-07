package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.service.ServiceGiocatore;
import it.univaq.dr2.tank19.service.ServicePartita;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carlo Centofanti
 * @created 07/06/2019
 */
@RestController
@RequestMapping("/partita")
public class ControllerGiocatoriPartita {

    private final ServicePartita servicePartita;
    private final ServiceGiocatore serviceGiocatore;

    public ControllerGiocatoriPartita(ServicePartita servicePartita, ServiceGiocatore serviceGiocatore) {
        this.servicePartita = servicePartita;
        this.serviceGiocatore = serviceGiocatore;
    }

    //TODO: questo metodo è solo per validare visivamente il funzionamento. translare e spostare in test
    @RequestMapping("/{idpartita}/get")
    public String getId(@PathVariable("idpartita") Long id) {
        servicePartita.findById(id).getListaIdGiocatori().iterator().forEachRemaining(aLong -> {
            System.out.println(serviceGiocatore.findById(aLong).getNome());
        });
        return "a";
    }
}
