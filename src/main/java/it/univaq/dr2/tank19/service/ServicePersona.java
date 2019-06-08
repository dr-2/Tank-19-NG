package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Persona;
import it.univaq.dr2.tank19.repository.RepositoryPersone;
import it.univaq.dr2.tank19.repository.RepositoryRuoli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ServicePersona implements UserService {
    @Autowired
    private RepositoryPersone repoPersone;
    @Autowired
    private RepositoryRuoli repoRuoli;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Persona persona){
        persona.setPassword(bCryptPasswordEncoder.encode(persona.getPassword()));
        persona.setRuoli(new HashSet<>(repoRuoli.findAll()));
        repoPersone.save(persona);
    }

    @Override
    public Persona findByUsername(String username){
        return repoPersone.findByUsername(username);
    }
}
