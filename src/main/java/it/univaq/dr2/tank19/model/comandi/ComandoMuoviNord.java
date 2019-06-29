package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ComandoMuoviNord implements Comando {
    @Autowired
    Environment env;

    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        Direzione direzione = Direzione.NORD;
        Integer velocita = oggettoDiGioco.getVelocita();
        Integer nuovaPosX = oggettoDiGioco.getPosX();
        Integer nuovaPosY = oggettoDiGioco.getPosY() - velocita;
        //Integer dimensione = oggettoDiGioco.getDimensioneHitbox();


    }
}
