package org.example2;

public class ResourceNotFoundException extends DomainException{
    public ResourceNotFoundException(String entityName, Long id) {
        super(String.format("%s con ID %d no fue encontrado", entityName, id));
    }
}
