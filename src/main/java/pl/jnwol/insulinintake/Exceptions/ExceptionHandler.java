package pl.jnwol.insulinintake.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value ={InsulinIntakeNotFoundException.class})
    public ResponseEntity<Void> notFound(){
        return ResponseEntity.notFound().build();
    }
}
