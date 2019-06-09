package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.OggettoDiGioco;
import it.univaq.dr2.tank19.repository.RepositoryOggettoDiGioco;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlo Centofanti
 * @created 09/06/2019
 */
@Service
public class ServiceOggettoDiGiocoImpl implements ServiceOggettoDiGioco {

    private final RepositoryOggettoDiGioco repositoryOggettoDiGioco;

    public ServiceOggettoDiGiocoImpl(RepositoryOggettoDiGioco repositoryOggettoDiGioco) {
        this.repositoryOggettoDiGioco = repositoryOggettoDiGioco;
    }

    @Override
    public Set<OggettoDiGioco> findAll() {
        Set<OggettoDiGioco> oggettoDiGiocoSet = new HashSet<>();
        repositoryOggettoDiGioco.findAll().iterator().forEachRemaining(oggettoDiGiocoSet::add);
        return oggettoDiGiocoSet;
    }

    @Override
    public OggettoDiGioco findById(Long aLong) {
        return repositoryOggettoDiGioco.findById(aLong).orElse(null);
    }

    @Override
    public OggettoDiGioco save(OggettoDiGioco object) {
        return repositoryOggettoDiGioco.save(object);
    }

    @Override
    public void delete(OggettoDiGioco object) {
        repositoryOggettoDiGioco.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryOggettoDiGioco.deleteById(aLong);
    }
}
