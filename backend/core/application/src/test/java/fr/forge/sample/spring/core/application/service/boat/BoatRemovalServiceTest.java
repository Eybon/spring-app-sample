package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatRemovalUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BoatRemovalServiceTest {

    private final BoatDatabase boatDatabase = Mockito.mock(BoatDatabase.class);
    private final BoatRemovalUseCase boatRemovalUseCase = new BoatRemovalService(boatDatabase);

    @Test
    void test_boatRemove_withNullParam() throws BoatNotExistException {
        // When
        Exception catchException = null;
        try {
            boatRemovalUseCase.execute(null);
        } catch (NullPointerException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).remove(Mockito.any());
        Assertions.assertNotNull(catchException);
        Assertions.assertEquals("Param 'boatName' must not be null", catchException.getMessage());
    }

    @Test
    void test_boatRemoval() throws BoatNotExistException {
        // Given
        String boatName = "Black Pearl";

        // When
         boatRemovalUseCase.execute(boatName);

        // Then
        Mockito.verify(boatDatabase, Mockito.times(1)).remove(Mockito.any());
    }
}
