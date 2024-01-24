package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatRemovalUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;

import java.util.Objects;

public class BoatRemovalService implements BoatRemovalUseCase {

    private final BoatDatabase boatDatabase;

    public BoatRemovalService(final BoatDatabase boatDatabase) {
        this.boatDatabase = boatDatabase;
    }

    @Override
    public void execute(String boatName) {
        Objects.requireNonNull(boatName, "Param 'boatName' must not be null");

        boatDatabase.remove(boatName);
    }
}
