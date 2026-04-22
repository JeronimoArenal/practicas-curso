package org.example4;

// Error cuando buscamos un ID que no existe
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, Long id) {
        super(entityName + " con ID " + id + " no encontrado.");
    }
}