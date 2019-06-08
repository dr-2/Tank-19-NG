package it.univaq.dr2.tank19.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ServiceSicurezza implements SecurityService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private ServiceDetailsPersona serviceDetailsPersona;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceSicurezza.class);

    @Override
    public String findLoggedInUsername(){
        Object detailsPersona = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(detailsPersona instanceof UserDetails){
            return ((UserDetails)detailsPersona).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password){
        UserDetails detailsPersona = serviceDetailsPersona.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(detailsPersona, password, detailsPersona.getAuthorities());
        authManager.authenticate(usernamePasswordAuthenticationToken);
        if(usernamePasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Login di %s avvenuto con successo!", username));
        }
    }

}