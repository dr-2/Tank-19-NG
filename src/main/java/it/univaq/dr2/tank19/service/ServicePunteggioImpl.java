package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Punteggio;
import it.univaq.dr2.tank19.repository.RepositoryPunteggio;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlo Centofanti
 * @created 07/06/2019
 */
@Service
public class ServicePunteggioImpl implements ServicePunteggio {

    private final RepositoryPunteggio repositoryPunteggio;

    public ServicePunteggioImpl(RepositoryPunteggio repositoryPunteggio) {
        this.repositoryPunteggio = repositoryPunteggio;
    }


    @Override
    public Set<Punteggio> findAll() {
        Set<Punteggio> punteggio = new HashSet<>();
        repositoryPunteggio.findAll().forEach(punteggio::add);
        return punteggio;
    }

    @Override
    public Punteggio findById(Long aLong) {
        return repositoryPunteggio.findById(aLong).orElse(null);
    }

    @Override
    public Punteggio save(Punteggio object) {
        return repositoryPunteggio.save(object);
    }

    @Override
    public void delete(Punteggio object) {
        repositoryPunteggio.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryPunteggio.deleteById(aLong);
    }
}
