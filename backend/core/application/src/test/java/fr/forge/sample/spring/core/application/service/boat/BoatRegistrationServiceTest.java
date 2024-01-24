package fr.forge.sample.spring.core.application.service.boat;

import fr.forge.sample.spring.core.application.port.in.boat.BoatAlreadyExistException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatRegistrationUseCase;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.boat.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BoatRegistrationServiceTest {

    private final BoatDatabase boatDatabase = Mockito.mock(BoatDatabase.class);
    private final BoatRegistrationUseCase boatRegistrationUseCase = new BoatRegistrationService(boatDatabase);

    @Test
    void test_boatRegistration_withNullParam() throws BoatAlreadyExistException {
        // When
        Exception catchException = null;
        try {
            boatRegistrationUseCase.execute(null);
        } catch (NullPointerException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).add(Mockito.any());
        Assertions.assertNotNull(catchException);
        Assertions.assertEquals("Object 'boat' must not be null", catchException.getMessage());
    }

    @Test
    void test_boatRegistration_withBoatNotExistInDatabase() throws BoatAlreadyExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Boat boat = new Boat(boatName, boatDescription, null);
        Mockito.when(boatDatabase.exist(Mockito.any())).thenReturn(false);

        // When
        boatRegistrationUseCase.execute(boat);

        // Then
        Mockito.verify(boatDatabase, Mockito.times(1)).add(Mockito.any());
    }

    @Test
    void test_boatRegistration_withBoatExistInDatabase() throws BoatAlreadyExistException {
        // Given
        String boatName = "Black Pearl";
        String boatDescription = "Navire le plus rapide des Caraibes";
        Boat boat = new Boat(boatName, boatDescription, null);
        Mockito.when(boatDatabase.exist(Mockito.any())).thenReturn(true);

        // When
        BoatAlreadyExistException catchException = null;
        try {
            boatRegistrationUseCase.execute(boat);
        } catch (BoatAlreadyExistException e) {
            catchException = e;
        }

        // Then
        Mockito.verify(boatDatabase, Mockito.never()).add(Mockito.any());
        Assertions.assertNotNull(catchException);
    }
}
