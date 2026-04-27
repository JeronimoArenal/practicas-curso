package org.exampleClase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Los streams o tuberias tienen 3 pasos fundamentales:
 * 1º (.stream) Abrimos el grifo personas.stream()
 * 2º Operaciones intermedias: .filter, .map, .sorted, etc
 * 3º Operacion final o resultado .forEach, .toList, .count
 */

public class LambdaExercices {


    /**
     * El uso de la clase anonima es una exageracion si no se implementan varias variables, ni se va a implementar otra cosa que
     * no sea el método abstracto de la interface funcional Predicate.
     *
     * Expresiones Lambda
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
     *
     * Es una expresión que representa la implementación del método abstracto de una Interface Funcional.
     * Funciona únicamente con Interfaces con 1 y solo 1 método abstracto siempre que los otros sean:
     * Métodos default: (Tienen lógica propia).
     * Métodos static: (Son herramientas de la interface).
     * Métodos heredados de Object: (Como equals o hashCode declarados en la interfaz)
     *
     * SCOPE
     * Variables Locales: Tanto las clases anónimas como las expresiones lambda solamente pueden capturar variables si son final o efectivamente final.
     * Atributos de Clase: Pueden acceder libremente a los atributos de la clase (this.xxxx) haciendo referencia al objeto contenedor
     *
     */

    //............................. ejemplo lambda ........................
    public static void ejemploLambda(List<Persona> personas) {
        personas.stream()
                .filter(p -> p.genero().equals(Genero.MASCULINO))
                .mapToDouble(p -> p.salario())
                .average()              //El método average devuelve Optional para proteger e codigo contra NullPointerexception
                                        //Es decir el metodo avisa que el resultado es opcional
                .ifPresent(System.out::println);

        //Otra manera de extraerlo
        var optionalSalario = personas.stream()
                .filter(p -> p.genero().equals(Genero.FEMENINO))
                .mapToDouble(p -> p.salario())
                .average();
        double salarioMedio = 0.0;
        if(optionalSalario.isPresent()){
            salarioMedio = optionalSalario.getAsDouble();
        }
        System.out.println(salarioMedio);

        /* Metodo por referencia.
        Si la expresion lambda lo único que hace es invocar al método que realiza el trabajo, es más eficiente pasar por referencia
        la dirección de dicho método, como es el caso de mapToDouble que lo unico que hace es invocar al metodo que recupera
        el salario
         */
        var optionalSalario1 = personas.stream()
                .filter(p -> p.genero().equals(Genero.FEMENINO))
                .mapToDouble(Persona :: salario)
                .average().orElse(0);
    }

    /*
    Hasta el momento hemos creado colecciones List en este caso asignando en memoria a través de un array con metodo add() que daría
    como resultado una colección de tamaño fijo con List.of
     */

    public static List<String> listaInmutable = List.of("Jerónimo", "Duglas", "Carolina");

    /*
    Object Ordering
    https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
     */

    //Error porque listaInmutable no implementa la interface Comparable
 /*   public static void ejemploLambda1(List<String> nombres) {
        Collections.sort(nombres);
        nombres.forEach(System.out::println);
    } */
    public static void ejemploLambda1(List<String> nombres) {
        // Creamos una copia local que SÍ se puede ordenar
        List<String> copia = new ArrayList<>(nombres);
        Collections.sort(copia);
        copia.forEach(System.out::println);     //Referencia a Método
    }

    /** Referencia a Métodos
     * Método de instancia de un objeto arbitrario:
     *     Sintaxis: Tipo :: nombreMetodo
     *     Ejemplo: Persona::salario (Equivale a p -> p.salario()).
     * Método estático:
     *     Sintaxis: Clase :: metodoEstatico
     *     Ejemplo: Integer::parseInt (Equivale a s -> Integer.parseInt(s)).
     * Método de instancia de un objeto específico:
     *     Sintaxis: instancia :: nombreMetodo
     *     Ejemplo: System.out::println (Equivale a x -> System.out.println(x)).
     * Referencia a un constructor:
     *     Sintaxis: Clase :: new
     *     Ejemplo: ArrayList::new (Equivale a () -> new ArrayList()).
     * @param personas
     */

    /*
    Reglas para poder usar ::
    Para que puedas sustituir una lambda por esta sintaxis de dos puntos, se deben cumplir

    Unicidad: La lambda debe consistir en una sola instrucción que sea la llamada a un método.
              Si hay cálculos, transformaciones extra o encadenamiento de métodos, la referencia no sirve.

    Simplicidad: La lambda solo debe llamar a un método y nada más.
                 Si intentamos hacer algo como .map(p -> p.nombre().toUpperCase()), ya no puedes usar porque hay una operación extra (toUpperCase).
    Correspondencia: Java debe ser capaz de entender que el objeto que fluye por el Stream es el "dueño" del método.
     */

    public static void ejemploLambdaCompareTo(List<Persona> personas) {
        // Tienes que pasarle el Comparator sí o sí porque el record no es "Comparable" por sí solo
        //Collections.sort(personas, Comparator.comparing(Persona::nombre));
        //Al añadir ell compareTo en la clase ahora podemos hacer :
        Collections.sort(personas);
        personas.forEach(System.out::println);
    }

    public static void ordenarPorNacimiento(List<Persona> lista) {
        // 1. Creamos la copia local (por si nos pasan una lista inmutable como List.of)
        List<Persona> copia = new ArrayList<>(lista);

        // 2. Ordenamos usando una Lambda
        // (p1, p2) -> ... es la forma de comparar dos personas
        copia.sort((p1, p2) -> p1.fechaNacimiento().compareTo(p2.fechaNacimiento()));

        // 3. Imprimimos el resultado
        System.out.println("--- Lista ordenada por fecha de nacimiento ---");
        copia.forEach(p -> System.out.println(p.nombre() + " nació el: " + p.fechaNacimiento()));
    }

    public static void ordenarPorNacimientoDirecto(List<Persona> personas) {
        // Ordenamos la lista original directamente
        personas.sort((p1, p2) -> p1.fechaNacimiento().compareTo(p2.fechaNacimiento()));
        // Imprimimos para verificar
        personas.forEach(p -> System.out.println(p.nombre() + " - " + p.fechaNacimiento()));
    }

}
