package it.univaq.dr2.tank19.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carlo Centofanti
 * @created 10/06/2019
 */

@RestController
public class RESTControllerConfigurazioni {

    @Value("${canvas.altezza}")
    Integer altezza;

    @Value("${canvas.larghezza}")
    Integer larghezza;

    @Value("${tank.velocita}")
    Integer velocita;

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
}
