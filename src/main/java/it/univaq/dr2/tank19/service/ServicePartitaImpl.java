package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.repository.RepositoryGiocatore;
import it.univaq.dr2.tank19.repository.RepositoryPartita;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServicePartitaImpl implements ServicePartita {

    private final RepositoryGiocatore repositoryGiocatore;
    private final RepositoryPartita repositoryPartita;

    public ServicePartitaImpl(RepositoryGiocatore repositoryGiocatore, RepositoryPartita repositoryPartita) {
        this.repositoryGiocatore = repositoryGiocatore;
        this.repositoryPartita = repositoryPartita;
    }

    @Override
    public Set<Partita> findAll() {
        Set<Partita> partite = new HashSet<>();
        repositoryPartita.findAll().forEach(partite::add);
        return partite;
    }

    @Override
    public Partita findById(Long aLong) {
        return repositoryPartita.findById(aLong).orElse(null);
    }

    @Override
    public Partita save(Partita object) {
        return repositoryPartita.save(object);
    }

    @Override
    public void delete(Partita object) {
        repositoryPartita.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryPartita.deleteById(aLong);
    }
}
