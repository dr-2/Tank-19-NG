package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.gioco.Proiettile;
import it.univaq.dr2.tank19.model.gioco.Tank;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(Tank tank) {
        if (tank.getProiettile() == null) {
            Proiettile proiettile = new Proiettile();
            Direzione direzione = tank.getDirezione();
            Posizione posizione = new Posizione();

            posizione.setPosY(tank.getPosizione().getPosY());
            posizione.setPosX(tank.getPosizione().getPosX());

            proiettile.setDirezione(direzione);
            proiettile.setPosizione(posizione);
            proiettile.setTank(tank);
            tank.setProiettile(proiettile);
        } else {
        }
    }
}
