package it.univaq.dr2.tank19.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceGiocatoreDetails implements UserDetailsService {
    private final ServiceGiocatore serviceGiocatore;

    public ServiceGiocatoreDetails(ServiceGiocatore serviceGiocatore) {
        this.serviceGiocatore = serviceGiocatore;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        serviceGiocatore.findById()

        return null;
    }
}
