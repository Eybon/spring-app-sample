package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatAlreadyExistException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatRegistrationUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;

import java.util.Objects;

public class BoatRegistrationService implements BoatRegistrationUseCase {

    private final BoatDatabase boatDatabase;

    public BoatRegistrationService(final BoatDatabase boatDatabase) {
        this.boatDatabase = boatDatabase;
    }

    @Override
    public void execute(Boat boat) throws BoatAlreadyExistException {
        Objects.requireNonNull(boat, "Object 'boat' must not be null");
        Objects.requireNonNull(boat.name(), "Field 'boat.name()' must not be null");

        if (boatDatabase.exist(boat.name())) {
            throw new BoatAlreadyExistException();
        } else {
            boatDatabase.add(boat);
        }
    }
}
