package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.oggettigioco.Muretto;
import it.univaq.dr2.tank19.repository.RepositoryMuretti;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceMurettiImpl implements ServiceMuretti {
    private final RepositoryMuretti repositoryMuretti;

    public ServiceMurettiImpl(RepositoryMuretti repositoryMuretti) {
        this.repositoryMuretti = repositoryMuretti;
    }

    @Override
    public Set<Muretto> findAll() {
        Set<Muretto> proiettili = new HashSet<>();
        repositoryMuretti.findAll().iterator().forEachRemaining(proiettili::add);
        return proiettili;
    }

    @Override
    public Muretto findById(Long aLong) {
        return repositoryMuretti.findById(aLong).orElse(null);
    }

    @Override
    public Muretto save(Muretto object) {
        return repositoryMuretti.save(object);
    }

    @Override
    public void delete(Muretto object) {
        repositoryMuretti.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryMuretti.deleteById(aLong);
    }
}
