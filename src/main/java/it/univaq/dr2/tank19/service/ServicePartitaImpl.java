package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.repository.RepositoryPartite;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServicePartitaImpl implements ServicePartita {

    private final RepositoryPartite repositoryPartite;

    public ServicePartitaImpl(RepositoryPartite repositoryPartite) {
        this.repositoryPartite = repositoryPartite;
    }

    @Override
    public Set<Partita> getPartite() {
        Set<Partita> partite = new HashSet<>();
        repositoryPartite.findAll().iterator().forEachRemaining(partite::add);
        return partite;
    }
}
