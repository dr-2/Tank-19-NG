package it.univaq.dr2.tank19.service;

import it.univaq.dr2.tank19.model.Giocatore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServiceGiocatoreDetails implements UserDetailsService {
    private final ServiceGiocatore serviceGiocatore;

    public ServiceGiocatoreDetails(ServiceGiocatore serviceGiocatore) {
        this.serviceGiocatore = serviceGiocatore;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Giocatore g = serviceGiocatore.findByUsername(s);
        g.setRuolo("ROLE_USER");

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(g.getRuolo()));
        User u = new User(g.getUsername(), g.getPassword(), auth);
        return u;
    }
}
