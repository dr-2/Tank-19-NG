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

    @NonNull
    private TipoOggetto tipoOggettoPadre;

    public Integer getXMax() {
        Integer retValue = null;
        if (tipoOggettoPadre == TipoOggetto.PROIETTILE) {
            retValue = posX + 5;
        } else if (tipoOggettoPadre == TipoOggetto.CARRO_ARMATO) {
            retValue = posX + 30;
        }
        return retValue;
    }

    public Integer getYMax() {
        Integer retValue = null;
        if (tipoOggettoPadre == TipoOggetto.PROIETTILE) {
            retValue = posY + 5;
        } else if (tipoOggettoPadre == TipoOggetto.CARRO_ARMATO) {
            retValue = posY + 30;
        }
        return retValue;
    }
}
