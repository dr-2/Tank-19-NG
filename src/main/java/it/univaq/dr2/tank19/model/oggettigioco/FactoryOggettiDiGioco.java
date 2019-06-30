package it.univaq.dr2.tank19.model.oggettigioco;

import java.util.ResourceBundle;


public class FactoryOggettiDiGioco {
    // Devo regolare l'accesso all'istanza da parte dei thread
    private volatile static FactoryOggettiDiGioco uniqueInstance;
    private static ResourceBundle rb = ResourceBundle.getBundle("application");

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
        //TODO: inserire dati sul Giocatore proprietario del tank e altre info mancanti
        // ESEMPIO:  Tank tank = Tank.builder().vita(100000).direzione(Direzione.OVEST).tipo(TipoOggetto.CARRO_ARMATO).partita(p1).posX(100).posY(100).velocita(1).build();
        Tank tank = Tank.builder()
                .vita(Integer.parseInt(rb.getString("tank.vita")))
                .velocita(Integer.parseInt(rb.getString("tank.velocita")))
                .hitbox(Integer.parseInt(rb.getString("tank.hitbox")))
                .build();

        return tank;
    }

    public OggettoDiGioco getLimiteMappa() {
        return new LimiteMappa();
    }

    public synchronized OggettoDiGioco getProiettile() {
        Proiettile proiettile = new Proiettile();
        proiettile.setVelocita(Integer.parseInt(rb.getString("proiettile.velocita")));
        proiettile.setVita(Integer.parseInt(rb.getString("proiettile.vita")));
        proiettile.setHitbox(Integer.parseInt(rb.getString("proiettile.hitbox")));

        return proiettile;
    }
}

