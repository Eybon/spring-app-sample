package fr.forge.sample.spring.core.application.port.out.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.model.boat.Boat;

public interface BoatDatabase {
    Boat get(String boatName) throws BoatNotExistException;
    void add(Boat boat);
    void update(Boat boat);
    void remove(String boatName);
    boolean exist(String boatName);
}
