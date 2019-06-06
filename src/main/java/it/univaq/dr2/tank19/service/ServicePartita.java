package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Partita;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ServicePartita {
    Set<Partita> getPartite();

}
