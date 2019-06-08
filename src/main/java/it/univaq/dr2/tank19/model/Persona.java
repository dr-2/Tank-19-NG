package it.univaq.dr2.tank19.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity // Hibernate crea la relativa tabella
@Table(name = "persone")
@Data // Lombok implementas automaticamente getter e setter
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany
    private Set<RuoloPersona> ruoli;
    private String status; //ad es. se l'utente è loggato
}