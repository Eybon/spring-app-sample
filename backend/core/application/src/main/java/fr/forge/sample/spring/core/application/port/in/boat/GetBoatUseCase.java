package fr.forge.sample.spring.core.application.port.in.boat;

import fr.forge.sample.spring.core.model.Boat;

public interface GetBoatUseCase {
    Boat execute(String boatName) throws BoatNotExistException;
}
