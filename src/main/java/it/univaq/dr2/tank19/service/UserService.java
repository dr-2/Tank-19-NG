package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Persona;

public interface UserService {

    void save(Persona persona);

    Persona findByUsername(String username);
}

