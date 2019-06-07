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
    private Integer id;
    private String username;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "privilegi", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "ruoli"))
    private Set<RuoloPersona> ruoli;
    private String status; //ad es. se l'utente è loggato
}