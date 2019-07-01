package it.univaq.dr2.tank19.model.collisione;

import it.univaq.dr2.tank19.model.TipoOggetto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryRegoleCollisioneImpl implements FactoryRegoleCollisione {
    private static FactoryRegoleCollisioneImpl ourInstance = new FactoryRegoleCollisioneImpl();

    public static FactoryRegoleCollisioneImpl getInstance() {
        return ourInstance;
    }

    private FactoryRegoleCollisioneImpl() {
    }

    @Override
    public RegolaCollisione getRegolaPer(TipoOggetto oggettoCheGeneraCollisione, TipoOggetto oggettoCheSubisceCollisione) {
        if (oggettoCheGeneraCollisione == TipoOggetto.PROIETTILE && oggettoCheSubisceCollisione == TipoOggetto.CARRO_ARMATO) {
            return new RegolaCollisioneProiettileTank();
        }
        if (oggettoCheGeneraCollisione == TipoOggetto.CARRO_ARMATO && oggettoCheSubisceCollisione == TipoOggetto.CARRO_ARMATO) {
            return new RegolaCollisioneNulla();
        }
        if (oggettoCheGeneraCollisione == TipoOggetto.CARRO_ARMATO && oggettoCheSubisceCollisione == TipoOggetto.LIMITE) {
            return new RegolaCollisioneNulla();
        }
        if (oggettoCheGeneraCollisione == TipoOggetto.PROIETTILE && oggettoCheSubisceCollisione == TipoOggetto.LIMITE) {
            return new RegolaCollisioneProiettileLimite();
        }
        if (oggettoCheGeneraCollisione == TipoOggetto.CARRO_ARMATO && oggettoCheSubisceCollisione == TipoOggetto.MURETTO){
            return new RegolaCollisioneTankMuretto();
        }
        if (oggettoCheGeneraCollisione == TipoOggetto.PROIETTILE && oggettoCheSubisceCollisione == TipoOggetto.MURETTO){
            return new RegolaCollisioneProiettileMuretto();
        }

        // Se non so cosa fare, loggo e non applico regole
        log.error("Caso di collisione non implementato. Viene applicata la regola nulla");
        return new RegolaCollisioneNulla();
    }
}
