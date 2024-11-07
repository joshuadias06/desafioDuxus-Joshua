package br.com.duxusdesafio.config;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.IntegranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IntegrantesIniciais implements CommandLineRunner {

    @Autowired
    private final IntegranteService integranteService;

    public IntegrantesIniciais(IntegranteService integranteService) {
        this.integranteService = integranteService;
    }

    @Override
    public void run(String... args) {
        if (integranteService.listarIntegrantes().isEmpty()) {
            Integrante[] integrantes = new Integrante[] {
                    new Integrante("Valorant", "Aspas", "Fragger", null),
                    new Integrante("Fortnite", "Bugha", "IGL", null),
                    new Integrante("Fortnite", "controlek1ng", "Fragger", null),
                    new Integrante("League of Legends", "Faker", "Mid laner", null),
                    new Integrante("Call of Duty", "Scump", "Submachine Gun", null),
                    new Integrante("Apex Legends", "Albralelie", "Support", null),
                    new Integrante("Valorant", "Shroud", "Streamer/Fragger", null),
                    new Integrante("Overwatch", "Jjonak", "Support", null),
                    new Integrante("Dota 2", "Miracle-", "Carry", null),
                    new Integrante("Counter-Strike", "s1mple", "AWPer", null)
            };

            for (Integrante integrante : integrantes) {
                integranteService.criarIntegrante(integrante);
            }
        }
    }
}
