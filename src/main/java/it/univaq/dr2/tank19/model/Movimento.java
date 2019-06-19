package it.univaq.dr2.tank19.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Movimento extends BaseEntity {

    static int xMax;
    static int yMax;
    static int velocita;

    @Autowired
    Environment env;

    @PostConstruct
    public void a() {
        velocita = Integer.parseInt(env.getProperty("tank.velocita"));
        xMax = Integer.parseInt(env.getProperty("canvas.altezza"));
        yMax = Integer.parseInt(env.getProperty("canvas.larghezza"));
    }

    public void muovimi(OggettoDiGioco oggettoDiGioco, Direzione direzione) {
        Integer posX = oggettoDiGioco.getPosizione().getPosX();
        Integer posY = oggettoDiGioco.getPosizione().getPosY();


        if (direzione == Direzione.NORD) {
            if (posY > 0) {
                posY = posY - velocita;
            }
        }
        if (direzione == Direzione.SUD) {
            if (posY < yMax - 20) {
                posY = posY + velocita;
            }
        }
        if (direzione == Direzione.EST) {
            if (posX < xMax - 20) {
                posX = posX + velocita;
            }
        }
        if (direzione == Direzione.OVEST) {
            if (posX > 0) {
                posX = posX - velocita;
            }
        }

        Posizione newPosizione = new Posizione();
        newPosizione.setPosX(posX);
        newPosizione.setPosY(posY);

        oggettoDiGioco.setPosizione(newPosizione);

    }
}