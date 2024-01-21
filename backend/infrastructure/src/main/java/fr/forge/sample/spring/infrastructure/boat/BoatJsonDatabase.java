package fr.forge.sample.spring.infrastructure.boat;

import fr.forge.json.datafile.Database;
import fr.forge.json.datafile.JsonDatabase;
import fr.forge.json.datafile.JsonDatabaseException;
import fr.forge.sample.spring.core.application.port.in.boat.BoatNotExistException;
import fr.forge.sample.spring.core.application.port.out.boat.BoatDatabase;
import fr.forge.sample.spring.core.model.Boat;
import fr.forge.sample.spring.infrastructure.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatJsonDatabase implements BoatDatabase {

    Logger LOGGER = LoggerFactory.getLogger(BoatJsonDatabase.class);

    private final Database<BoatReferential> database;
    private BoatReferential boatReferential;

    public BoatJsonDatabase(DatabaseConfig databaseConfig) {
        this.database = new JsonDatabase<>(BoatReferential.class, databaseConfig.getBoatFile());
        this.loadDatabase();
    }

    @Override
    public Boat getBoat(String boatName) throws BoatNotExistException {
        LOGGER.info("[DB] Method getBoat : {}", boatName);

        return this.boatReferential.all()
                .stream()
                .filter(x -> boatName.equals(x.name()))
                .findFirst()
                .orElseThrow(BoatNotExistException::new);
    }

    @Override
    public void addBoat(Boat boat) {
        // TODO à implementer
    }

    private void loadDatabase() {
        try {
            this.boatReferential = database.load();
        } catch (JsonDatabaseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void syncDatabase() {
        try {
            this.database.save(this.boatReferential);
        } catch (JsonDatabaseException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
