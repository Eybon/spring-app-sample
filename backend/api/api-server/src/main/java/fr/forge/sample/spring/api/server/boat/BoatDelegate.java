package fr.forge.sample.spring.api.server.boat;

import fr.forge.sample.spring.api.generated.BoatApiDelegate;
import fr.forge.sample.spring.api.generated.BoatWeb;
import fr.forge.sample.spring.api.server.commons.GenericWebException;
import fr.forge.sample.spring.core.application.port.in.boat.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BoatDelegate implements BoatApiDelegate {

    Logger LOGGER = LoggerFactory.getLogger(BoatDelegate.class);

    private final GetBoatUseCase getBoatUseCase;
    private final BoatRegistrationUseCase boatRegistrationUseCase;
    private final BoatUpdateUseCase boatUpdateUseCase;
    private final BoatRemovalUseCase boatRemovalUseCase;
    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    public BoatDelegate(final GetBoatUseCase getBoatUseCase,
                        final BoatRegistrationUseCase boatRegistrationUseCase,
                        final BoatUpdateUseCase boatUpdateUseCase,
                        final BoatRemovalUseCase boatRemovalUseCase) {
        this.getBoatUseCase = getBoatUseCase;
        this.boatRegistrationUseCase = boatRegistrationUseCase;
        this.boatUpdateUseCase = boatUpdateUseCase;
        this.boatRemovalUseCase = boatRemovalUseCase;
    }

    public ResponseEntity<BoatWeb> getBoat(String boatName) {
        LOGGER.info("[API] GET /boat/{}", boatName);
        try {
            return ResponseEntity.ok(
                    BoatWebMapper.fromModel(getBoatUseCase.execute(boatName)));
        } catch (BoatNotExistException e) {
            LOGGER.info("[API] GET /boat/{} --> Boat not exist", boatName);
            throw new GenericWebException(HttpStatus.NOT_FOUND, String.format("Le bateau `%s` n'existe pas", boatName));
        }
    }

    public ResponseEntity<Void> postBoat(BoatWeb boatWeb) {
        LOGGER.info("[API] POST /boat with body : {}", boatWeb.toString());
        try {
            boatRegistrationUseCase.execute(BoatWebMapper.fromWeb(boatWeb));
        } catch (BoatAlreadyExistException e) {
            LOGGER.info("[API] POST /boat with name : {} --> Boat already exist", boatWeb.getName());
            throw new GenericWebException(HttpStatus.CONFLICT, String.format("Le bateau `%s` existe deja", boatWeb.getName()));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> putBoat(BoatWeb boatWeb) {
        LOGGER.info("[API] PUT /boat with body : {}", boatWeb.toString());
        try {
            boatUpdateUseCase.execute(BoatWebMapper.fromWeb(boatWeb));
        } catch (BoatNotExistException e) {
            LOGGER.info("[API] PUT /boat with name : {} --> Boat not exist", boatWeb.getName());
            throw new GenericWebException(HttpStatus.NOT_FOUND, String.format("Le bateau `%s` n'existe pas, impossible de faire la modification", boatWeb.getName()));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteBoat(String boatName) {
        LOGGER.info("[API] DELETE /boat/{}", boatName);
        boatRemovalUseCase.execute(boatName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Resource> getBoatImage(String boatName) {
        LOGGER.info("[API] GET /boat/img/{}", boatName);
        try {
            Resource boatImg = this.loadFileByResourcePath(
                    this.getBoatUseCase.execute(boatName).img());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + boatImg.getFilename() + "\"")
                    .body(boatImg);
        } catch (BoatNotExistException e) {
            LOGGER.info("[API] GET /boat/img/{} --> Boat not exist", boatName);
            throw new GenericWebException(HttpStatus.NOT_FOUND, String.format("Le bateau `%s` n'existe pas", boatName));
        } catch (IOException ex) {
            LOGGER.info("[API] GET /boat/img/{} --> Picture of boat not exist", boatName);
            throw new GenericWebException(HttpStatus.NOT_FOUND, String.format("Aucune image existante pour le bateau `%s`", boatName));
        }
    }

    private Resource loadFileByResourcePath(String imgPath) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:bdd/img/" + imgPath);
        // Verification que l'image existe
        resource.getFile().exists();
        return resource;
    }
}
