package it.univaq.dr2.tank19.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    @Value("${canvas.altezza}")
    Integer altezzaMappa;

    @Value("${canvas.larghezza}")
    Integer larghezzaMappa;

    @Value("${tank.velocita}")
    Integer velocitaTank;

    @Value("${proiettile.velocita}")
    Integer velocitaProiettile;
}
