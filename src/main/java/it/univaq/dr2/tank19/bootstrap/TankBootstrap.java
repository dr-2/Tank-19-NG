package it.univaq.dr2.tank19.bootstrap;

import it.univaq.dr2.tank19.model.*;
import it.univaq.dr2.tank19.service.ServiceGiocatore;
import it.univaq.dr2.tank19.service.ServicePartita;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TankBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ServicePartita servicePartita;
    private final ServiceGiocatore serviceGiocatore;

    public TankBootstrap(ServicePartita servicePartita, ServiceGiocatore serviceGiocatore) {
        this.servicePartita = servicePartita;
        this.serviceGiocatore = serviceGiocatore;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.warn("****************** STO CARICANDO I DATI INIZIALI DAL BOOTSTRAP. Disabilitare in produzione *******************");
        loadData();
    }

    private void loadData() {
        List<Partita> partite = new ArrayList<>(2);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        Partita p = new Partita();
        Giocatore g = Giocatore.builder().username("Carlo").ruolo(TipoRuolo.ROLE_USER.toString()).partita(p).password(encoder.encode("password")).build();
        Punteggio punti = Punteggio.builder().punti(33).build();
        g.setPunteggio(punti);

        g.aggiungiPunti(-34);

        OggettoDiGioco tank = OggettoDiGioco.builder().partita(p).posX(100).posY(100).build();
        p.getOggettiDiGioco().add(tank);


        p.getGiocatori().add(g);

        servicePartita.save(p);


        Partita p2 = new Partita();
        Giocatore g2 = new Giocatore();
        g2.setUsername("Valeria");
        g2.setPartita(p2);

        OggettoDiGioco tank2 = OggettoDiGioco.builder().partita(p2).posX(100).posY(100).build();
        p2.getOggettiDiGioco().add(tank2);

        p2.getGiocatori().add(g2);

        servicePartita.save(p2);


        Giocatore g4 = new Giocatore();
        g4.setUsername("Gio");
        g4.setPartita(p2);

        p2.getGiocatori().add(g4);

        servicePartita.save(p2);

        Giocatore g3 = new Giocatore();
        g3.setUsername("Agnese");
        g3.setPartita(p);
        g3.setPassword(encoder.encode("password"));

        OggettoDiGioco tank3 = OggettoDiGioco.builder().partita(p).posX(500).posY(500).build();
        p.getOggettiDiGioco().add(tank3);

        p.getGiocatori().add(g3);


        servicePartita.save(p);


    }
}
