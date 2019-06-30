package it.univaq.dr2.tank19.model.comandi;

public class ComandoTankStrategyFactory {
    private static ComandoTankStrategyFactory ourInstance = new ComandoTankStrategyFactory();

    public static ComandoTankStrategyFactory getInstance() {
        return ourInstance;
    }

    private ComandoTankStrategyFactory() {

    }

    public Comando getComandoMovimento() {
        return new ComandoMovimento();
    }

    public Comando getComandoFuoco() {
        return new ComandoFuoco();
    }
}
