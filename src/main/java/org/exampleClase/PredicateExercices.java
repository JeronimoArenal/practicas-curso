package org.exampleClase;

import java.util.List;
import java.util.function.Predicate;

public class PredicateExercices {


    /**
     * El uso de la clase anonima es una exageracion si  no se implementan varias variables, ni se va a impllmentar otra cosa que
     * no seaell método abstracto de la interface funcional Predicate.
     *
     * Expresiones Lambda
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
     *
     * Es un método anónimo, que por lo genral se utilizan para implmentar el método abstracto de las Interfaces Funcionales.
     * Tanto las clases anónimas como las expresiones lambda sollamente se pueden relacionar con variables locales que sean
     * explicitamente final.
     *
     */
    //............................. iteratorSeveralConditions ........................
    public static void ejemploPredicate(List<Persona> personas) {
        personas.stream()
                .filter(p -> p.genero().equals(Genero.FEMENINO))
                .forEach(System.out::println);
    }

}
