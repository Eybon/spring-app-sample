package fr.forge.sample.spring.app;

import fr.forge.sample.spring.core.application.port.in.boat.GetBoatUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.application.service.boat.GetBoatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public GetBoatUseCase getBoatUseCase(BoatDatabase boatDatabase) {
        return new GetBoatService(boatDatabase);
    }

}
