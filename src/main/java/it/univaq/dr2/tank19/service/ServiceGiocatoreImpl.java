package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Giocatore;
import it.univaq.dr2.tank19.repository.RepositoryGiocatore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceGiocatoreImpl implements ServiceGiocatore {
    private final RepositoryGiocatore repositoryGiocatore;

    public ServiceGiocatoreImpl(RepositoryGiocatore repositoryGiocatore) {
        this.repositoryGiocatore = repositoryGiocatore;
    }

    @Override
    public Set<Giocatore> findAll() {
        Set<Giocatore> owners = new HashSet<>();
        repositoryGiocatore.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Giocatore findById(Long aLong) {
        return repositoryGiocatore.findById(aLong).orElse(null);
    }

    @Override
    public Giocatore save(Giocatore object) {
        return repositoryGiocatore.save(object);
    }

    @Override
    public void delete(Giocatore object) {
        repositoryGiocatore.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repositoryGiocatore.deleteById(aLong);
    }

    @Override
    public Giocatore findByUsername(String username) {
        return repositoryGiocatore.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Giocatore g = this.findByUsername(s);
        g.setRuolo("ROLE_USER");

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(g.getRuolo()));
        User u = new User(g.getUsername(), g.getPassword(), auth);
        return u;
    }
}
