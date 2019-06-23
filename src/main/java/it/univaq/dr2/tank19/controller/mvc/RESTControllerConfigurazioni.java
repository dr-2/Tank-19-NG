package it.univaq.dr2.tank19.controller.mvc;

import it.univaq.dr2.tank19.service.ServiceGiocatore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carlo Centofanti
 * @created 10/06/2019
 */

@RestController
public class RESTControllerConfigurazioni {
    private final ServiceGiocatore serviceGiocatore;

    @Value("${canvas.altezza}")
    Integer altezza;

    @Value("${canvas.larghezza}")
    Integer larghezza;

    @Value("${tank.velocita}")
    Integer velocita;

    public RESTControllerConfigurazioni(ServiceGiocatore serviceGiocatore) {
        this.serviceGiocatore = serviceGiocatore;
    }

    @RequestMapping("/configurazioni/canvas/altezza")
    public Integer getAltezzaCanvas() {
        return this.altezza;
    }

    @RequestMapping("/configurazioni/canvas/larghezza")
    public Integer getLarghezzaCanvas() {
        return this.larghezza;
    }

    @RequestMapping("/configurazioni/tank/velocita")
    public Integer getVelocitaTank() {
        return this.velocita;
    }

    @RequestMapping("/configurazioni/getMioTankId")
    public String getMyId() {
        return serviceGiocatore.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMioTank().getId().toString();
    }

    @RequestMapping("/configurazioni/getPartitaId")
    public String getPartitaId() {
        return serviceGiocatore.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getPartita().getId().toString();
    }

    @RequestMapping("/configurazioni/userinfo/username")
    public String getMyUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
