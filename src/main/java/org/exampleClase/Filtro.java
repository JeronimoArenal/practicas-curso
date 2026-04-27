package org.exampleClase;

import java.util.function.Predicate;

public class Filtro implements Predicate<Persona> {
    @Override
    public boolean test(Persona persona) {
        return false;
    }
}
