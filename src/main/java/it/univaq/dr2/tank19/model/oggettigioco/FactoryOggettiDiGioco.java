package it.univaq.dr2.tank19.model.oggettigioco;

public class FactoryOggettiDiGioco {
    // Devo regolare l'accesso all'istanza da parte dei thread
    private volatile static FactoryOggettiDiGioco uniqueInstance;

    private FactoryOggettiDiGioco() {
    }

    public static FactoryOggettiDiGioco getInstance() {
        if (uniqueInstance == null) {   // Controllo rapido. Se esiste già non impatta sulle prestazioni
            synchronized (FactoryOggettiDiGioco.class) { // Eseguo in maniera Atomica e lenta
                if (uniqueInstance == null) { //Controllo lento. Ora sono sicuro di essere il primo thread e posso creare l'istanza
                    uniqueInstance = new FactoryOggettiDiGioco();
                }
            }
        }
        return uniqueInstance; // Posso ritornare l'istanza unica della factory Singleton
    }

    public OggettoDiGioco getTank() {
        return new Tank();
    }

    public OggettoDiGioco getLimiteMappa() {
        return new LimiteMappa();
    }

    public synchronized OggettoDiGioco getProiettile() {
        return new Proiettile();
    }
}

