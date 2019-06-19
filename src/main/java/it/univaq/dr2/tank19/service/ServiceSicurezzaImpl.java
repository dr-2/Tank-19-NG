package it.univaq.dr2.tank19.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceSicurezzaImpl implements ServiceSicurezza {
    private final AuthenticationManager authManager;
    private final ServiceGiocatore serviceGiocatore;

    public ServiceSicurezzaImpl(AuthenticationManager authManager, ServiceGiocatore serviceGiocatore) {
        this.authManager = authManager;
        this.serviceGiocatore = serviceGiocatore;
    }

    @Override
    public String findLoggedInUsername() {
        Object detailsPersona = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (detailsPersona instanceof UserDetails) {
            return ((UserDetails) detailsPersona).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails detailsPersona = serviceGiocatore.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(detailsPersona, password, detailsPersona.getAuthorities());
        authManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.debug(String.format("Login di %s avvenuto con successo!", username));
        }
    }

}
