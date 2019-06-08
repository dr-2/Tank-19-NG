package it.univaq.dr2.tank19.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String Username, String password);
}
