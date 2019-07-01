package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.oggettigioco.Tank;
import it.univaq.dr2.tank19.repository.RepositoryTank;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class ServiceTankImpl implements ServiceTank {

    private final RepositoryTank repositoryTank;

    public ServiceTankImpl(RepositoryTank repositoryTank) {
        this.repositoryTank = repositoryTank;
    }

    @Override
    public Set<Tank> findAll() {
        Set<Tank> tankSet = new HashSet<>();
        repositoryTank.findAll().iterator().forEachRemaining(tankSet::add);
        return tankSet;
    }

    @Override
    public Tank findById(Long aLong) {
        return repositoryTank.findById(aLong).orElse(null);
    }

    @Override
    public Tank save(Tank object) {
        return repositoryTank.save(object);
    }

    @Override
    public void delete(Tank object) {
        repositoryTank.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryTank.deleteById(aLong);
    }
}
