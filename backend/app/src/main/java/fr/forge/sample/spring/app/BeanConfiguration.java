package fr.forge.sample.spring.app;

import fr.forge.sample.spring.core.application.port.in.boat.BoatRegistrationUseCase;
import fr.forge.sample.spring.core.application.port.in.boat.BoatRemovalUseCase;
import fr.forge.sample.spring.core.application.port.in.boat.BoatUpdateUseCase;
import fr.forge.sample.spring.core.application.port.in.boat.GetBoatUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.application.service.boat.BoatRegistrationService;
import fr.forge.sample.spring.core.application.service.boat.BoatRemovalService;
import fr.forge.sample.spring.core.application.service.boat.BoatUpdateService;
import fr.forge.sample.spring.core.application.service.boat.GetBoatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public GetBoatUseCase getBoatUseCase(BoatDatabase boatDatabase) {
        return new GetBoatService(boatDatabase);
    }

    @Bean
    public BoatRegistrationUseCase boatRegistrationUseCase(BoatDatabase boatDatabase) {
        return new BoatRegistrationService(boatDatabase);
    }

    @Bean
    public BoatUpdateUseCase boatUpdateUseCase(BoatDatabase boatDatabase) {
        return new BoatUpdateService(boatDatabase);
    }

    @Bean
    public BoatRemovalUseCase boatRemovalUseCase(BoatDatabase boatDatabase) {
        return new BoatRemovalService(boatDatabase);
    }
}
