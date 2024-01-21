package fr.forge.sample.spring.infrastructure;

import fr.forge.sample.spring.infrastructure.boat.BoatJsonDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseBean {
    @Bean
    DatabaseConfig databaseConfig() {
        return new DatabaseConfig();
    }

    @Bean
    BoatJsonDatabase boatDatabase(DatabaseConfig databaseConfig) {
        return new BoatJsonDatabase(databaseConfig);
    }
}
