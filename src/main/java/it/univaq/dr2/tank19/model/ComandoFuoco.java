package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.gioco.Tank;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(Tank tank, Direzione direzione) {

    }
}
