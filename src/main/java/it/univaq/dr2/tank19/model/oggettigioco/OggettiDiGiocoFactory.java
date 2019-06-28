package it.univaq.dr2.tank19.model.oggettigioco;

import java.lang.reflect.InvocationTargetException;

public class OggettiDiGiocoFactory {
    private static OggettiDiGiocoFactory ourInstance = new OggettiDiGiocoFactory();

    public static OggettiDiGiocoFactory getInstance() {
        return ourInstance;
    }

    private OggettiDiGiocoFactory() {
    }

    public OggettoDiGioco getTank() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return (OggettoDiGioco) Class.forName(Tank.class.getName()).getConstructor().newInstance();
    }

    public OggettoDiGioco getProiettile() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (OggettoDiGioco) Class.forName(Proiettile.class.getName()).getConstructor().newInstance();
    }
}
