package it.univaq.dr2.tank19.model;

import it.univaq.dr2.tank19.model.gioco.Tank;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
public interface Comando {
    void esegui(Tank tank);
}
