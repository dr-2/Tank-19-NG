package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.oggettigioco.Muretto;
import it.univaq.dr2.tank19.repository.RepositoryMuretto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceMurettoImpl implements ServiceMuretto {

    private final RepositoryMuretto repositoryMuretto;

    public ServiceMurettoImpl(RepositoryMuretto repositoryMuretto) {
        this.repositoryMuretto = repositoryMuretto;
    }

    @Override
    public Set<Muretto> findAll() {
        Set<Muretto> muretti = new HashSet<>();
        repositoryMuretto.findAll().iterator().forEachRemaining(muretti::add);
        return muretti;
    }

    @Override
    public Muretto findById(Long aLong) {
        return repositoryMuretto.findById(aLong).orElse(null);
    }

    @Override
    public Muretto save(Muretto object) {
        return repositoryMuretto.save(object);
    }

    @Override
    public void delete(Muretto object) {
        repositoryMuretto.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryMuretto.deleteById(aLong);
    }
}
