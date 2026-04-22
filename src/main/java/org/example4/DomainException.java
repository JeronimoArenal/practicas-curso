package org.example4;

// Error cuando algo falla en las reglas de negocio (ej: nombre vacío)
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}