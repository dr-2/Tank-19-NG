package it.univaq.dr2.tank19.bootstrap;

import it.univaq.dr2.tank19.model.Partita;
import it.univaq.dr2.tank19.repository.RepositoryPartite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TankBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RepositoryPartite repositoryPartite;

    public TankBootstrap(RepositoryPartite repositoryPartite) {
        this.repositoryPartite = repositoryPartite;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.warn("****************** STO CARICANDO I DATI INIZIALI DAL BOOTSTRAP. Disabilitare in produzione *******************");
        repositoryPartite.saveAll(getPartite());
    }

    private List<Partita> getPartite() {
        List<Partita> partite = new ArrayList<>(2);

        Partita partita1 = new Partita();
        partita1.setGiocatoreA("Carlo");
        partita1.setGiocatoreB("Agnese");
        partite.add(partita1);

        return partite;
    }
}
