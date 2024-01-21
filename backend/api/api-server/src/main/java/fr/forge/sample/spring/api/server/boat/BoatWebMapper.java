package fr.forge.sample.spring.api.server.boat;

import fr.forge.sample.spring.api.generated.BoatWeb;
import fr.forge.sample.spring.core.model.Boat;

public class BoatWebMapper {
    static BoatWeb fromModel(Boat boat) {
        return BoatWeb.builder()
                .name(boat.name())
                .description(boat.description())
                .build();
    }
}