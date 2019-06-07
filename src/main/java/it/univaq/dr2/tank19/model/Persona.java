package it.univaq.dr2.tank19.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Hibernate crea la relativa tabella
@Data // Lombok implementas automaticamente getter e setter
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String username;
    private String email;
    private String password;
    @Autowired
    private TipoRuoloPersona ruolo;
    private boolean attivo = false; // True se l'utente è loggato
    private boolean verificato = false; //True se l'email dell'utente è già stata verificata
}