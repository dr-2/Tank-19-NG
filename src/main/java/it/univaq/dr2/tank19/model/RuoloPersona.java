package it.univaq.dr2.tank19.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "ruoli")
@Data
public class RuoloPersona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private TipoRuoloPersona tipoRuolo;
    private String descrizione;
}
