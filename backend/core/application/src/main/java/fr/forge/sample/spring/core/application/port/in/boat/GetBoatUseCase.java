package fr.forge.sample.spring.core.application.port.in.boat;

import fr.forge.sample.spring.core.model.boat.Boat;

public interface GetBoatUseCase {
    Boat execute(String boatName) throws BoatNotExistException;
}
