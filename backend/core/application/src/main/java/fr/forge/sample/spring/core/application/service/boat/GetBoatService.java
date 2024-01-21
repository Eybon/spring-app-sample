package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.in.boat.GetBoatUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.Boat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class GetBoatService implements GetBoatUseCase {

    Logger LOGGER = LoggerFactory.getLogger(GetBoatService.class);

    private final BoatDatabase boatDatabase;

    public GetBoatService(final BoatDatabase boatDatabase) {
        this.boatDatabase = boatDatabase;
    }

    @Override
    public Boat execute(String boatName) throws BoatNotExistException {
        Objects.requireNonNull(boatName, "Parameter 'boatName' must not be null");

        return this.boatDatabase.getBoat(boatName);
    }
}
