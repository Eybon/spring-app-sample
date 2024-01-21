package fr.forge.sample.spring.api.server.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
public class GenericWebException extends RuntimeException {
    HttpStatus httpStatus;
    String message;
}
