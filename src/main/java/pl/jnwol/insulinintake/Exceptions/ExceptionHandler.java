package pl.jnwol.insulinintake.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value ={InsulinIntakeNotFoundException.class})
    public ResponseEntity<Void> notFound(){
        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Void> illegalArgument(){
        return ResponseEntity.badRequest().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {DataBaseIsEmpty.class})
    public ResponseEntity<Void> empty(){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
