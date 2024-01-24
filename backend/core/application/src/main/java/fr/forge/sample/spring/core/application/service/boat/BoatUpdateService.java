package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatUpdateUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;

import java.util.Objects;

public class BoatUpdateService implements BoatUpdateUseCase {

    private final BoatDatabase boatDatabase;

    public BoatUpdateService(final BoatDatabase boatDatabase) {
        this.boatDatabase = boatDatabase;
    }

    @Override
    public void execute(Boat boat) throws BoatNotExistException {
        Objects.requireNonNull(boat, "Object 'boat' must not be null");
        Objects.requireNonNull(boat.name(), "Field 'boat.name()' must not be null");

        if (!boatDatabase.exist(boat.name())) {
            throw new BoatNotExistException();
        } else {
            boatDatabase.update(boat);
        }
    }
}
