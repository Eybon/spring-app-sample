package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatUpdateUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BoatUpdateServiceTest {

    private final BoatDatabase boatDatabase = Mockito.mock(BoatDatabase.class);
    private final BoatUpdateUseCase boatUpdateUseCase = new BoatUpdateService(boatDatabase);

    @Test
    void test_boatUpdate_withNullParam() throws BoatNotExistException {
        // When
        Exception catchException = null;
        try {
            boatUpdateUseCase.execute(null);
        } catch (NullPointerException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).update(Mockito.any());
        Assertions.assertNotNull(catchException);
        Assertions.assertEquals("Object 'boat' must not be null", catchException.getMessage());
    }

    @Test
    void test_boatUpdate_withBoatExistInDatabase() throws BoatNotExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Boat boat = new Boat(boatName, boatDescription, null);
        Mockito.when(boatDatabase.exist(Mockito.any())).thenReturn(true);

        // When
         boatUpdateUseCase.execute(boat);

        // Then
        Mockito.verify(boatDatabase, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    void test_boatUpdate_withBoatNotExistInDatabase() throws BoatNotExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Boat boat = new Boat(boatName, boatDescription, null);
        Mockito.when(boatDatabase.exist(Mockito.any())).thenReturn(false);

        // When
        BoatNotExistException catchException = null;
        try {
            boatUpdateUseCase.execute(boat);
        } catch (BoatNotExistException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).update(Mockito.any());
        Assertions.assertNotNull(catchException);
    }
}
