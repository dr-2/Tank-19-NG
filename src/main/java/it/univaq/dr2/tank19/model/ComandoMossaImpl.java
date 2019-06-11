package it.univaq.dr2.tank19.model;

import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoMossaImpl implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        System.out.println("STO MUOVENDO L'OGGETTO DI GIOCO... (forse dovrei prima implementarlo questo comando XD)");
    }
}
