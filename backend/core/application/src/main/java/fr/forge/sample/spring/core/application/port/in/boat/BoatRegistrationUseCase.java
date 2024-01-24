package fr.forge.sample.spring.core.application.port.in.boat;

import fr.forge.sample.spring.core.model.boat.Boat;

public interface BoatRegistrationUseCase {
    void execute(Boat boat) throws BoatAlreadyExistException;
}
