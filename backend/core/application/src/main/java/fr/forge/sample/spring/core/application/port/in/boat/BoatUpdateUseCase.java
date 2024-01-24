package fr.forge.sample.spring.core.application.port.in.boat;

import fr.forge.sample.spring.core.model.boat.Boat;

public interface BoatUpdateUseCase {
    void execute(Boat boat) throws BoatNotExistException;
}
