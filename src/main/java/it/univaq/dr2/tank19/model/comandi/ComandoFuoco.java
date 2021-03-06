package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.oggettigioco.FactoryOggettiDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import it.univaq.dr2.tank19.model.oggettigioco.Proiettile;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import org.springframework.stereotype.Component;

/**
 * @author Carlo Centofanti
 * @created 08/06/2019
 */
@Component
public class ComandoFuoco implements Comando {
    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        if (oggettoDiGioco.getProiettile() == null) {
            FactoryOggettiDiGioco factoryOggettiDiGioco = FactoryOggettiDiGioco.getInstance();
            Proiettile proiettile = null;

            proiettile = (Proiettile) factoryOggettiDiGioco.getProiettile();
            Direzione direzione = oggettoDiGioco.getDirezione();

            assert proiettile != null;
            proiettile.setPosY(getSpawnY(oggettoDiGioco));
            proiettile.setPosX(getSpawnX(oggettoDiGioco));

            proiettile.setDirezione(direzione);
            proiettile.setTank((Tank) oggettoDiGioco);
            oggettoDiGioco.setProiettile(proiettile);
        } else { // TODO: al momento un tank non può sparare più di un proiettile
        }
    }

    private Integer getSpawnX(OggettoDiGioco oggettoDiGioco) {
        Integer retVal = oggettoDiGioco.getPosX();
        if (oggettoDiGioco.getDirezione() == Direzione.EST) retVal = retVal + oggettoDiGioco.getDimensioneHitbox();
        if (oggettoDiGioco.getDirezione() == Direzione.NORD)
            retVal = retVal + (oggettoDiGioco.getDimensioneHitbox() / 2);
        if (oggettoDiGioco.getDirezione() == Direzione.SUD)
            retVal = retVal + (oggettoDiGioco.getDimensioneHitbox() / 2);
        if (oggettoDiGioco.getDirezione() == Direzione.OVEST) retVal = retVal - 5;


        return retVal;
    }

    private Integer getSpawnY(OggettoDiGioco oggettoDiGioco) {
        Integer retVal = oggettoDiGioco.getPosY();
        if (oggettoDiGioco.getDirezione() == Direzione.SUD) retVal = retVal + oggettoDiGioco.getDimensioneHitbox();
        if (oggettoDiGioco.getDirezione() == Direzione.EST)
            retVal = retVal + (oggettoDiGioco.getDimensioneHitbox() / 2);
        if (oggettoDiGioco.getDirezione() == Direzione.OVEST)
            retVal = retVal + (oggettoDiGioco.getDimensioneHitbox() / 2);
        if (oggettoDiGioco.getDirezione() == Direzione.NORD) retVal = retVal - 5;

        return retVal;
    }


}
