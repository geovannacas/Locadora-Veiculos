package com.poo;

import com.poo.application.facades.MenuFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
// Vamos ser explícitos sobre onde encontrar TODOS os componentes para garantir
// que nada seja perdido durante a inicialização.
@ComponentScan(basePackages = "com.poo")
@EnableMongoRepositories(basePackages = "com.poo.infraestructure.repositories")
public class Main implements CommandLineRunner {

    private final MenuFacade menuFacade;

    public Main(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class)
            .headless(false)
            .run(args);
    }

    /** 
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Com a configuração correta, o menuFacade não será mais nulo.
        menuFacade.exibirMenuPrincipal();
    }
}
