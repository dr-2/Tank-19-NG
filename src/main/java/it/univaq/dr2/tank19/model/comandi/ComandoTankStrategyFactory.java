package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComandoTankStrategyFactory {
    private static ComandoTankStrategyFactory ourInstance = new ComandoTankStrategyFactory();

    public static ComandoTankStrategyFactory getInstance() {
        return ourInstance;
    }

    private ComandoTankStrategyFactory() {

    }

    public Comando getComandoMovimento() {
        return new ComandoMovimento();
    }

    public Comando getComandoMuoviA(Direzione direzione) {
        Comando retCmd = null;
        switch (direzione) {
            case NORD:
                retCmd = new ComandoMuoviNord();
                break;
            case EST:
                retCmd = new ComandoMuoviEst();
                break;
            case SUD:
                retCmd = new ComandoMuoviSud();
                break;
            case OVEST:
                retCmd = new ComandoMuoviOvest();
                break;
            default:
                log.warn("Sto usando un comando deprecato. indagare a fondo");
                retCmd = new ComandoMovimento();

        }
        return retCmd;
    }

    public Comando getComandoFuoco() {
        return new ComandoFuoco();
    }
}
