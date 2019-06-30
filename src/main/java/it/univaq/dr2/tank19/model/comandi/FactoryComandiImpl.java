package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FactoryComandiImpl implements FactoryComandi {
    private static FactoryComandiImpl ourInstance = new FactoryComandiImpl();

    public static FactoryComandiImpl getInstance() {
        return ourInstance;
    }

    private FactoryComandiImpl() {

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
