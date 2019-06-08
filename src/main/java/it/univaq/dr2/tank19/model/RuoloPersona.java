package it.univaq.dr2.tank19.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ruoli")
@Data
public class RuoloPersona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nome;
    private TipoRuoloPersona tipo;
    private String descrizione;
    @ManyToMany(mappedBy = "ruoli")
    private Set<Persona> users;
}
