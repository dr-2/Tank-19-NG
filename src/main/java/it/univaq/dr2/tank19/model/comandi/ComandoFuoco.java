package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.collisione.Collisione;
import it.univaq.dr2.tank19.model.collisione.RilevatoreCollisioni;
import it.univaq.dr2.tank19.model.oggettigioco.OggettiDiGiocoFactory;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.stereotype.Component;


@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        if (oggettoDiGioco.getProiettile() == null) {
            OggettiDiGiocoFactory oggettiDiGiocoFactory = OggettiDiGiocoFactory.getInstance();
            Proiettile proiettile = null;

            proiettile = (Proiettile) oggettiDiGiocoFactory.getProiettile();
            Direzione direzione = oggettoDiGioco.getDirezione();
            proiettile.setDirezione(direzione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);
            // Quando viene generato, il proiettile si trova in corrispondenza del Tank generatore
            proiettile.setPosX(oggettoDiGioco.getPosX());
            proiettile.setPosY(oggettoDiGioco.getPosY());


            assert proiettile != null;
            Integer nuovaPosX = proiettile.getPosX() + 15;
            Integer nuovaPosY = proiettile.getPosX() + 15;

            Proiettile tempProiettile = (Proiettile) oggettiDiGiocoFactory.getProiettile(); //Proiezione della futura posizione del proiettile
            tempProiettile.setPosX(nuovaPosX);
            tempProiettile.setPosY(nuovaPosY);
            tempProiettile.setId(proiettile.getId());
            tempProiettile.setTank(proiettile.getTank());

            Collisione collisione = new RilevatoreCollisioni();
            Boolean collide = collisione.generaCollisione(tempProiettile);

            if (!collide) {
                proiettile.setPosX(nuovaPosX);
                proiettile.setPosY(nuovaPosY);
            } else {
                System.out.println("BOOM!");
            }


        } else { // TODO: al momento un tank non può sparare più di un proiettile
        }
    }




}