package it.univaq.dr2.tank19.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "posizione")
public class Posizione extends BaseEntity {
    @NonNull
    private Integer posX;
    @NonNull
    private Integer posY;
}
