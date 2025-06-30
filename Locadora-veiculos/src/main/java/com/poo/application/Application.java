package com.poo.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void Run() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            System.out.println("Sprinsg Framework inicializado com sucesso!");
        }
    }
}