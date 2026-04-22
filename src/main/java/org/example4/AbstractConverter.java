package org.example4;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase base genérica para convertir Entities <-> DTOs.
 *
 * @param <E> Tipo de la Entity
 * @param <D> Tipo del DTO
 */
public abstract class AbstractConverter<E, D> {

    public abstract D fromEntity(E entity);

    public abstract E fromDTO(D dto);

    /**
     * Convierte una lista de entidades en una lista de DTOs.
     * List<? extends E>: Le dice a Java: "Acepto una lista de E o de cualquier cosa que herede de E".
     */
    public List<D> fromEntityList(List<? extends E> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .map(this::fromEntity)
                .toList();
    }

    /**
     * Convierte una lista de DTOs en una lista de entidades.
     * List<? extends D>: Le dice a Java: "Acepto una lista de D o de cualquier cosa que herede de D".
     */
    public List<E> fromDTOList(List<? extends D> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .map(this::fromDTO)
                .toList();
    }

}
