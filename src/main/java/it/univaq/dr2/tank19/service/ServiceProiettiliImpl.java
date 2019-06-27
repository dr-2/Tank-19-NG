package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.gioco.Proiettile;
import it.univaq.dr2.tank19.repository.RepositoryProiettili;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceProiettiliImpl implements ServiceProiettili {
    private final RepositoryProiettili repositoryProiettili;

    public ServiceProiettiliImpl(RepositoryProiettili repositoryProiettili) {
        this.repositoryProiettili = repositoryProiettili;
    }

    @Override
    public Set<Proiettile> findAll() {
        Set<Proiettile> proiettili = new HashSet<>();
        repositoryProiettili.findAll().iterator().forEachRemaining(proiettili::add);
        return proiettili;
    }

    @Override
    public Proiettile findById(Long aLong) {
        return repositoryProiettili.findById(aLong).orElse(null);
    }

    @Override
    public Proiettile save(Proiettile object) {
        return repositoryProiettili.save(object);
    }

    @Override
    public void delete(Proiettile object) {
        repositoryProiettili.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryProiettili.deleteById(aLong);
    }
}
