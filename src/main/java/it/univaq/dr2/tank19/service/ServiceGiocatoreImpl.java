package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Giocatore;
import it.univaq.dr2.tank19.repository.RepositoryGiocatore;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceGiocatoreImpl implements ServiceGiocatore {
    private final RepositoryGiocatore repositoryGiocatore;

    public ServiceGiocatoreImpl(RepositoryGiocatore repositoryGiocatore) {
        this.repositoryGiocatore = repositoryGiocatore;
    }

    @Override
    public Set<Giocatore> findAll() {
        Set<Giocatore> owners = new HashSet<>();
        repositoryGiocatore.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Giocatore findById(Long aLong) {
        return repositoryGiocatore.findById(aLong).orElse(null);
    }

    @Override
    public Giocatore save(Giocatore object) {
        return repositoryGiocatore.save(object);
    }

    @Override
    public void delete(Giocatore object) {
        repositoryGiocatore.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryGiocatore.deleteById(aLong);
    }
}
