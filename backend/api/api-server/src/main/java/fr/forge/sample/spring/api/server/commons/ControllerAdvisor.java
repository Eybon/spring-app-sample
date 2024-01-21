package fr.forge.sample.spring.api.server.commons;

import fr.forge.sample.spring.api.generated.ErrorWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericWebException.class)
    public ResponseEntity<ErrorWeb> handleGenericException(GenericWebException exception) {
        return new ResponseEntity<>(ErrorWeb.builder()
                    .status(exception.getHttpStatus().value())
                    .message(exception.getMessage())
                    .build(),
                exception.getHttpStatus());
    }
}
