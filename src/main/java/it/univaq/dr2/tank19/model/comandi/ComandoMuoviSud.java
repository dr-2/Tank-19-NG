package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.Collisione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;

public class ComandoMuoviSud implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        oggettoDiGioco.setDirezione(Direzione.SUD);
        Integer velocita = oggettoDiGioco.getVelocita();
        Integer nuovaPosX = oggettoDiGioco.getPosX();
        Integer nuovaPosY = oggettoDiGioco.getPosY() + velocita;
        Integer vecchiaPosY = oggettoDiGioco.getPosY();
        //Integer dimensione = oggettoDiGioco.getDimensioneHitbox();

        //Prova a muovere
        oggettoDiGioco.setPosX(nuovaPosX);
        oggettoDiGioco.setPosY(nuovaPosY);

        //verifico se collido
        Collisione collisione = new RilevatoreCollisioni();
        Boolean collide = collisione.isColliding(oggettoDiGioco);

        if (collide) {
            // annullo movimento
            // annullo movimento e applico la collisione
            collisione.applicaCollisione();
            oggettoDiGioco.setPosY(vecchiaPosY);
            // TODO: segnala collisione
        }
    }
}
