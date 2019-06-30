package it.univaq.dr2.tank19.model.comandi;

import it.univaq.dr2.tank19.model.Direzione;
import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.model.oggettigioco.OggettoDiGioco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Component
public class ComandoMovimento implements Comando {

    static int xMax;
    static int yMax;
    static int velocita;

    @Autowired
    Environment env;

    @PostConstruct
    public void a() {
        velocita = Integer.parseInt(Objects.requireNonNull(env.getProperty("tank.velocita")));
        xMax = Integer.parseInt(Objects.requireNonNull(env.getProperty("canvas.altezza")));
        yMax = Integer.parseInt(Objects.requireNonNull(env.getProperty("canvas.larghezza")));
    }

    @Override
    public void esegui(OggettoDiGioco oggettoDiGioco) {
        Direzione direzione = oggettoDiGioco.getDirezione();
        Integer posX = oggettoDiGioco.getPosX();
        Integer posY = oggettoDiGioco.getPosY();
        Integer velocita = oggettoDiGioco.getVelocita() != null ? oggettoDiGioco.getVelocita() : ComandoMovimento.velocita;
        Integer dimensioneHitbox = oggettoDiGioco.getDimensioneHitbox();
        Partita partitaInCorso = oggettoDiGioco.getPartita();

        if (direzione == Direzione.NORD) {
            if (posY > 0) {
                posY = posY - velocita;
            }
            else{
                if(oggettoDiGioco.getTipo().toString() == "PROIETTILE"){
                    System.out.println("BOOM!");
                    System.out.println(partitaInCorso.getOggettiPartita().toArray().length);
                    partitaInCorso.cancellaProiettile(oggettoDiGioco);
                    System.out.println(partitaInCorso.getOggettiPartita().toArray().length);
                    // ToDo: Aggiungere comportamento relativo a esplosione proiettile

                }
            }
        }
        if (direzione == Direzione.SUD) {
            if (posY < yMax - dimensioneHitbox) {
                posY = posY + velocita;
            }
            else{
                if(oggettoDiGioco.getTipo().toString() == "PROIETTILE"){
                    System.out.println("BOOM!"); // ToDo: Aggiungere comportamento relativo a esplosione proiettile
                }
            }
        }
        if (direzione == Direzione.EST) {
            if (posX < xMax - dimensioneHitbox) {
                posX = posX + velocita;
            }
            else{
                if(oggettoDiGioco.getTipo().toString() == "PROIETTILE"){
                    System.out.println("BOOM!"); // ToDo: Aggiungere comportamento relativo a esplosione proiettile
                }
            }
        }
        if (direzione == Direzione.OVEST) {
            if (posX > 0) {
                posX = posX - velocita;
            }
            else{
                if(oggettoDiGioco.getTipo().toString() == "PROIETTILE"){
                    System.out.println("BOOM!"); // ToDo: Aggiungere comportamento relativo a esplosione proiettile
                }
            }
        }

        oggettoDiGioco.setPosX(posX);
        oggettoDiGioco.setPosY(posY);

        //oggettoDiGioco.setDirezione(direzione);

    }

}
