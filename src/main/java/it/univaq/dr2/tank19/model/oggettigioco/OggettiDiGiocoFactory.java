package it.univaq.dr2.tank19.model.oggettigioco;

import java.lang.reflect.InvocationTargetException;

public class OggettiDiGiocoFactory {
    // Devo regolare l'accesso all'istanza da parte dei thread
    private volatile static OggettiDiGiocoFactory uniqueInstance;

    private OggettiDiGiocoFactory() {
    }

    public static OggettiDiGiocoFactory getInstance() {
        if (uniqueInstance == null) {   // Controllo rapido. Se esiste già non impatta sulle prestazioni
            synchronized (OggettiDiGiocoFactory.class) { // Eseguo in maniera Atomica e lenta
                if (uniqueInstance == null) { //Controllo lento. Ora sono sicuro di essere il primo thread e posso creare l'istanza
                    uniqueInstance = new OggettiDiGiocoFactory();
                }
            }
        }
        return uniqueInstance; // Posso ritornare l'istanza unica della factory Singleton
    }

    public OggettoDiGioco getTank() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return (OggettoDiGioco) Class.forName(Tank.class.getName()).getConstructor().newInstance();
    }

    public synchronized OggettoDiGioco getProiettile() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (OggettoDiGioco) Class.forName(Proiettile.class.getName()).getConstructor().newInstance();
    }
}

