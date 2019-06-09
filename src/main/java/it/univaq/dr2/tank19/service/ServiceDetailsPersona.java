package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Persona;
import it.univaq.dr2.tank19.model.RuoloPersona;
import it.univaq.dr2.tank19.repository.RepositoryPersone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceDetailsPersona implements UserDetailsService {
    @Autowired
    private RepositoryPersone repoPersone;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Persona persona = repoPersone.findByUsername(username);
        if(persona == null) throw new UsernameNotFoundException(username);
        Set<GrantedAuthority> grantedAuth = new HashSet<>();
        for(RuoloPersona ruolo : persona.getRuoli()){
            grantedAuth.add(new SimpleGrantedAuthority(ruolo.getNome()));
        }
        return new User(persona.getUsername(), persona.getPassword(), grantedAuth);

    }

}
