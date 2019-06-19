package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Posizione;
import it.univaq.dr2.tank19.repository.RepositoriPosizione;

import java.util.HashSet;
import java.util.Set;

public class ServicePosizioneImpl implements ServicePosizione {
    private final RepositoriPosizione repositoriPosizione;

    public ServicePosizioneImpl(RepositoriPosizione repositoriPosizione) {
        this.repositoriPosizione = repositoriPosizione;
    }

    @Override
    public Set<Posizione> findAll() {
        Set<Posizione> posizioni = new HashSet<>();
        repositoriPosizione.findAll().forEach(posizioni::add);
        return posizioni;
    }

    @Override
    public Posizione findById(Long aLong) {
        return repositoriPosizione.findById(aLong).orElse(null);
    }

    @Override
    public Posizione save(Posizione object) {
        return repositoriPosizione.save(object);
    }

    @Override
    public void delete(Posizione object) {
        repositoriPosizione.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoriPosizione.deleteById(aLong);
    }
}
