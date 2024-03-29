package fr.forge.sample.spring.infrastructure.boat;

import fr.forge.sample.spring.core.model.boat.Boat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoatReferential implements Serializable {
    Set<Boat> boats = new HashSet<>();
    public List<Boat> all() {
        return new ArrayList<>(this.boats);
    }
    public void add(Boat boat) {
        boats.add(boat);
    }
    public void remove(String boatName) {
        boats.removeIf(x -> x.name().equals(boatName));
    }
}
