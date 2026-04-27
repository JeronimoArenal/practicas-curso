package org.exampleClase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExercices {


    /**
     * El uso de la clase anonima es una exageracion si  no se implementan varias variables, ni se va a impllmentar otra cosa que
     * no seaell método abstracto de la interface funcional Predicate.
     *
     * Expresiones Lambda
     * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
     *
     * Es un método anónimo, que por lo genral se utilizan para implmentar el método abstracto de las Interfaces Funcionales.
     * Tanto las clases anónimas como las expresiones lambda sollamente se pueden relacionar con variables locales que sean
     * explicitamente final. Es decir que no se pude cambiar el valor de las variables declaradas.
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
        Si la expresion lambda lo único que hace es invocar al metodo que realiza el trabajo, es más eficiente pasar por referencia
        la direccion de dicho método, como es el caso de mapToDouble que llo unico que hace es invocar all metodo que recupera
        el salario
         */
        var optionalSalario1 = personas.stream()
                .filter(p -> p.genero().equals(Genero.FEMENINO))
                .mapToDouble(Persona :: salario)
                .average().orElse(0);
    }

    /*
    Hasta el momento hemos creado colecciones List en este caso asignando en memoria a través de un array con metodo add() que daría
    como resultado una coleccion de tamaño fijo con List.of
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
        copia.forEach(System.out::println);
    }

    public static void ejemploLambdaCompareTo(List<Persona> personas) {
        // Tienes que pasarle el Comparator sí o sí porque el record no es "Comparable" por sí solo
        //Collections.sort(personas, Comparator.comparing(Persona::nombre));
        //Al añadir ell compareTo en la clase ahora podemos hacer :
        Collections.sort(personas);
        personas.forEach(System.out::println);

    }

}
