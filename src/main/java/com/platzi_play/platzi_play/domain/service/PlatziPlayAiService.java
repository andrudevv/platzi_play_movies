package com.platzi_play.platzi_play.domain.service;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PlatziPlayAiService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de Gestion de Peliculas {{plataform}}.
            Usa menos de 120 caracteres y hazlo con el estilo de platzi.
            """)
    String generateGreeting(@V("plataform") String plataform);

    @SystemMessage("""
            Eres un experto en cine que recomienda peliculas personalizdas segun los gustos del ususario.
            Debes recomendar maximo 3 peliculas
            No incluyas peliculas que estén por fuera de la plataforma PlatziPlay
            """)
    String generateMovieSuggestion(@UserMessage String userMessage);
}
