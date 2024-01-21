package fr.forge.sample.spring.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "database")
public class DatabaseConfig {
    private String boatFile;
}
