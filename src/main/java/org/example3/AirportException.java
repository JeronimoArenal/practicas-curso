package org.example3;

public class AirportException extends RuntimeException {

    //...................................... Constructor ........................................
    public AirportException(String message) {
        super(message);     // Llama al constructor de la clase "padre"  Runtimeexception
    }
}