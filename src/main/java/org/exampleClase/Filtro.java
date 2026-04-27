package org.exampleClase;

import java.util.function.Predicate;

/**
 * Predicate es una Interface Funcional(un sólo método) que representa un filtro en el objeto que recibe devolviendo True o False.
 * Tiene un único método abstracto llamado test(T t), utilizado especialmente para operaciones de filtrado
 */
public class Filtro implements Predicate<Persona> {
    @Override
    public boolean test(Persona p) {
        return p.salario() > 3000;
    }
}
