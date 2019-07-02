package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioneImpl;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ComandoMuoviNord implements Comando {

    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        oggettoDiGioco.setDirezione(Direzione.NORD);
        Integer velocita = oggettoDiGioco.getVelocita();
        Integer nuovaPosX = oggettoDiGioco.getPosX();
        Integer nuovaPosY = oggettoDiGioco.getPosY() - velocita;
        Integer vecchiaPosY = oggettoDiGioco.getPosY();
        //Integer dimensione = oggettoDiGioco.getDimensioneHitbox();

        //Prova a muovere
        oggettoDiGioco.setPosX(nuovaPosX);
        oggettoDiGioco.setPosY(nuovaPosY);

        //verifico se collido
        RilevatoreCollisione rilevatoreCollisione = new RilevatoreCollisioneImpl();
        Boolean collide = rilevatoreCollisione.isColliding(oggettoDiGioco);

        if (collide) {
            // annullo movimento e applico la rilevatoreCollisione
            rilevatoreCollisione.applicaCollisione();
            oggettoDiGioco.setPosY(vecchiaPosY);
            // TODO: segnala rilevatoreCollisione
        }

    }
}
