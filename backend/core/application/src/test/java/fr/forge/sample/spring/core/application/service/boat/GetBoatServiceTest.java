package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.in.boat.GetBoatUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GetBoatServiceTest {
    private final BoatDatabase boatDatabase = Mockito.mock(BoatDatabase.class);
    private final GetBoatUseCase getBoatUseCase = new GetBoatService(boatDatabase);

    @Test
    void test_getBoat_withNullParam() throws BoatNotExistException {
        // When
        Exception catchException = null;
        try {
            getBoatUseCase.execute(null);
        } catch (NullPointerException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).getBoat(Mockito.any());
        Assertions.assertNotNull(catchException);
        Assertions.assertEquals("Parameter 'boatName' must not be null", catchException.getMessage());
    }

    @Test
    void test_getBoat_withBoatExistInDatabase() throws BoatNotExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Mockito.when(boatDatabase.getBoat(Mockito.any()))
                .thenReturn(new Boat(boatName, boatDescription, null));

        // When
        Boat boat = getBoatUseCase.execute(boatName);

        // Then
        Assertions.assertEquals(boatName, boat.name());
        Assertions.assertEquals(boatDescription, boat.description());
    }

    @Test
    void test_getBoat_withBoatNotExistInDatabase() throws BoatNotExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Mockito.when(boatDatabase.getBoat(Mockito.any()))
                .thenThrow(new BoatNotExistException());

        // When
        BoatNotExistException catchException = null;
        try {
            getBoatUseCase.execute(boatName);
        } catch (BoatNotExistException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.times(1)).getBoat(Mockito.any());
        Assertions.assertNotNull(catchException);
    }
}
