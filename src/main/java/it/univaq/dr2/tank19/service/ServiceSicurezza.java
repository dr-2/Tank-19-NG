package it.univaq.dr2.tank19.service;

public interface ServiceSicurezza {

    String findLoggedInUsername();

    void autoLogin(String Username, String password);
}