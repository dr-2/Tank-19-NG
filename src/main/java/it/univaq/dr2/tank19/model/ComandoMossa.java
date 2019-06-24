package it.univaq.dr2.tank19.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoMossa implements Comando {

    static int xMax;
    static int yMax;
    static int velocita;

    @Autowired
    Environment env;

    @PostConstruct
    public void a() {
        velocita = Integer.parseInt(Objects.requireNonNull(env.getProperty("tank.velocita")));
        xMax = Integer.parseInt(Objects.requireNonNull(env.getProperty("canvas.altezza")));
        yMax = Integer.parseInt(Objects.requireNonNull(env.getProperty("canvas.larghezza")));
    }

    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco, Direzione direzione) {
        Integer posX = oggettoDiGioco.getPosizione().getPosX();
        Integer posY = oggettoDiGioco.getPosizione().getPosY();


        if (direzione == Direzione.NORD) {
            if (posY > 0) {
                posY = posY - velocita;
            }
        }
        if (direzione == Direzione.SUD) {
            if (posY < yMax - 30) {
                posY = posY + velocita;
            }
        }
        if (direzione == Direzione.EST) {
            if (posX < xMax - 30) {
                posX = posX + velocita;
            }
        }
        if (direzione == Direzione.OVEST) {
            if (posX > 0) {
                posX = posX - velocita;
            }
        }
        Posizione newPosizione = oggettoDiGioco.getPosizione();
        newPosizione.setPosX(posX);
        newPosizione.setPosY(posY);

        oggettoDiGioco.setPosizione(newPosizione);
        oggettoDiGioco.setDirezione(direzione);
    }
}
