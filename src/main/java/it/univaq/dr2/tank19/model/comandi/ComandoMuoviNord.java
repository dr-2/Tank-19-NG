package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.Collisione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.oggettigioco.OggettiDiGiocoFactory;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.stereotype.Component;

@Component
public class ComandoMuoviNord implements Comando {

    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        assert oggettoDiGioco.getDirezione() == Direzione.NORD;
        Direzione direzione = oggettoDiGioco.getDirezione();
        Integer velocita = oggettoDiGioco.getVelocita();
        Integer nuovaPosX = oggettoDiGioco.getPosX();
        Integer nuovaPosY = oggettoDiGioco.getPosY() - velocita;
        //Integer dimensione = oggettoDiGioco.getDimensioneHitbox();

        OggettiDiGiocoFactory oggettiDiGiocoFactory = OggettiDiGiocoFactory.getInstance();

        Tank tempTank = (Tank) oggettiDiGiocoFactory.getTank();
        tempTank.setPosX(nuovaPosX);
        tempTank.setPosX(nuovaPosY);
        tempTank.setId(oggettoDiGioco.getId());

        Collisione collisione = new RilevatoreCollisioni();
        Boolean collide = collisione.generaCollisione(tempTank);

        if (!collide) {
            oggettoDiGioco.setPosX(nuovaPosX);
            oggettoDiGioco.setPosY(nuovaPosY);
        } else {
            System.out.println("COLLIDE A NORD. VIENE SOLO RUOTATO");
        }


    }
}
