package fr.forge.sample.spring.core.application.port.out.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.model.Boat;

public interface BoatDatabase {
    Boat getBoat(String boatName) throws BoatNotExistException;
    void addBoat(Boat boat);
}
