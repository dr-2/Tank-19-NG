package it.univaq.dr2.tank19.bootstrap;

import it.univaq.dr2.tank19.model.*;
import it.univaq.dr2.tank19.model.oggettigioco.Tank;
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


        Partita p1 = new Partita();

        Giocatore g = Giocatore.builder().username("Carlo").ruolo(TipoRuolo.ROLE_USER.toString()).partita(p1).password(encoder.encode("p")).build();
        Punteggio punti = Punteggio.builder().punti(33).build();
        g.setPunteggio(punti);

        g.aggiungiPunti(-34);

        Tank tank = Tank.builder().direzione(Direzione.OVEST).tipo(TipoOggetto.CARRO_ARMATO).partita(p1).posX(100).posY(100).velocita(1).build();
        p1.getTanks().add(tank);
        g.setMioTank(tank);


        p1.getGiocatori().add(g);
        p1.setStato(TipoStatoPartita.CREAZIONE);
        p1.setNumeroGiocatoriUmani(2);


        servicePartita.save(p1);


        Partita p2 = new Partita();
        Giocatore g2 = new Giocatore();
        g2.setUsername("Valeria");
        g2.setPartita(p2);
        g2.setPassword(encoder.encode("p"));

        Tank tankValeria = Tank.builder().tipo(TipoOggetto.CARRO_ARMATO).direzione(Direzione.SUD).partita(p2).velocita(1).posX(550).posY(550).build();
        p2.getTanks().add(tankValeria);

        p2.getGiocatori().add(g2);
        g2.setMioTank(tankValeria);

        servicePartita.save(p2);


        Giocatore g4 = new Giocatore();
        g4.setUsername("Gio");
        g4.setPartita(p2);
        g4.setPassword(encoder.encode("p"));

        Tank tankGio = Tank.builder().tipo(TipoOggetto.CARRO_ARMATO).partita(p2).direzione(Direzione.EST).velocita(1).posX(500).posY(500).build();
        p2.getTanks().add(tankGio);

        p2.getGiocatori().add(g4);
        g4.setMioTank(tankGio);

        servicePartita.save(p2);

        Giocatore g3 = new Giocatore();
        g3.setUsername("Agnese");
        g3.setPartita(p1);
        g3.setPassword(encoder.encode("p"));

        Tank tank3 = Tank.builder().velocita(1).direzione(Direzione.SUD).tipo(TipoOggetto.CARRO_ARMATO).partita(p1).posX(200).posY(200).build();
        p1.getTanks().add(tank3);

        p1.getGiocatori().add(g3);
        g3.setMioTank(tank3);


        servicePartita.save(p1);


    }
}
