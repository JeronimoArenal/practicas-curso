package org.exampleClase;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // 1. Instanciamos la clase (creamos el objeto)
        //TraversingCollection travCol = new TraversingCollection();
        //travCol.hola();
        //arrayNumerosEnteros();
        //listaMethod();
        //listaMethodBuilder();
        /*
         * Para que cada método trabaje con su copia (clonamos) la lista y la pasamos como parámetro
         */
        List<Persona> listaDePersonas = listaMethodBuilder();
        TraversingCollection.iteratorOneCondition(new ArrayList<>(listaDePersonas));	//Se la pasamos al método del iterador
        TraversingCollection.iteratorSeveralConditions(new ArrayList<>(listaDePersonas));
        TraversingCollection.eliminarMujeres(new ArrayList<>(listaDePersonas));
        LambdaExercices.ejemploLambda(new ArrayList<>(listaDePersonas));
       // LambdaExercices.ejemploLambda1(LambdaExercices.listaInmutable);
        LambdaExercices.ejemploLambda2(new ArrayList<>(listaDePersonas));

    }

    public static void arrayNumerosEnteros() {
        Integer[] arrayNumerosEnteros = {1, 2, 3, 4, 5};

        // Arrays.asList convierte el array en una lista de tamaño fijo
        List<Integer> numerosEnteros = Arrays.asList(arrayNumerosEnteros);
        /*Una coleccion obtenida directamente a partir de un array es de tamaño fijo y no se pueden
         * añadir ni eliminar elementos, pero si modificar; aunque la lista es fija en tamaño no es
         * inmutable en contenido. Aunque no puedas hacer .add(), sí se puede hacer .set(index, valor)
         */
        //numerosEnteros.add(6); se puede havr

        System.out.println(numerosEnteros);

    }


    //................................. Metodo NO recomendado .............................
    public static void listaMethod() {
        /**
         * Se puede utilizar cualquiera de las dos expresiones. Y podemos utilizar var dentro de
         * un método por ser local
         */
        //List<Persona> personas = new ArrayList<>();
        var personas = new ArrayList<Persona>();

        // 2. Añadimos personas
        var persona1 = Persona.builder()
                .nombre("Duglas")
                .primerApellido("Gonźalez")
                .segundoApellido("Villamizar")
                .fechaNacimiento(LocalDate.of(1995, Month.APRIL, 15))
                .genero(Genero.MASCULINO)
                .build();

        var persona2 = Persona.builder()
                .nombre("Carolina")
                .primerApellido("Garzón")
                .segundoApellido("Becerra")
                .fechaNacimiento(LocalDate.of(2000, Month.OCTOBER, 10))
                .genero(Genero.FEMENINO)
                .build();

        var persona3 = Persona.builder()
                .nombre("María")
                .primerApellido("Garzón")
                .segundoApellido("González")
                .fechaNacimiento(LocalDate.of(2005, Month.DECEMBER, 14))
                .genero(Genero.FEMENINO)
                .build();

        var persona4 = Persona.builder()
                .nombre("Jerónimo")
                .primerApellido("Arenal")
                .segundoApellido("Gómez")
                .fechaNacimiento(LocalDate.of(1989, Month.MAY, 22))
                .genero(Genero.MASCULINO)
                .build();


        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);
        personas.add(persona4);


        // 3. Imprimir la lista
        personas.forEach(p -> System.out.println(
                        p.nombre() + " " +
                                p.primerApellido() + " " +
                                p.segundoApellido() + " " +
                                p.fechaNacimiento()
                )
        );

        System.out.println(personas);

    }

    //......................... Método SI recomendado ...........................
    public static List<Persona>  listaMethodBuilder() {

        /**
         * En lugar de llamar al metodo ad varias veces, es preferible agregar elementos a la lista
         * de la siguiente forma y no sería de tamaño fijo, ya que no se ha obtenido de un array.
         */

        var personas = new ArrayList<Persona>();

        // 2. Añadimos personas usando el Builder record
        personas.add(Persona.builder()
                .nombre("Duglas")
                .primerApellido("Gonźalez")
                .segundoApellido("Villamizar")
                .fechaNacimiento(LocalDate.of(1995, Month.APRIL, 15))
                .genero(Genero.MASCULINO) // Asumiendo que Genero es un Enum
                .salario((1500.00))
                .build());

        personas.add(Persona.builder()
                .nombre("Carolina")
                .primerApellido("Garzón")
                .segundoApellido("Becerra")
                .genero(Genero.FEMENINO)
                .fechaNacimiento(LocalDate.of(2000, Month.OCTOBER, 10))
                .salario((2500.00))
                .build());

        personas.add(Persona.builder()
                .nombre("María")
                .primerApellido("Garzón")
                .segundoApellido("González")
                .fechaNacimiento(LocalDate.of(2005, Month.DECEMBER, 14))
                .genero(Genero.FEMENINO)
                .salario((3500.00))
                .build());

        personas.add(Persona.builder()
                .nombre("Jerónimo")
                .primerApellido("Arenal")
                .segundoApellido("Gómez")
                .fechaNacimiento(LocalDate.of(1989, Month.MAY, 22))
                .genero(Genero.MASCULINO)
                .salario((4500.00))
                .build());

        // 3. Imprimir la lista
        //.forEach
        System.out.println("=== Listado inicial de Personas (.forEach) ===");
        personas.forEach(p -> System.out.println(
                        p.nombre() + " " +
                                p.primerApellido() + " " +
                                p.segundoApellido() + " " +
                                p.fechaNacimiento()
                )
        );

        return personas;
    }


    /**
     * Las operaciones de agregado implican convertir la coleccion en un flujo (stream) de elementos que al circular por una
     * tuberia imaginaria o pipeline se entiende una secuencia de metodos  de la clase Stream, es decir una secuencia
     * de operaciones de agregado, es decir, métodos para obtener un resultado agrupado de elementos del fllujo
     *
     * una tuberia o pipeline, tiene un origen  que puede ser un array, una coleccion un socket, un fichero, una consullta a Db, etc
     * tambien la tuberiaa tiene cero, una o muchas operaciones intermedia y UNA SOLA operación.
     *
     * LLo primero es utilizar ell método stream(), que tambien podria ser parallelStream(), para convertir lla coleccion en un flujo
     * de elementos del mismo tipo de la coleccion
     *
     * A artir de tener un flujo de elementos entran a funcionar los métodos de la clase Stream, operaciones intermedias,
     * como podria ser el metodo .filter para permitor  que solmanete circule e próximo nivell de lla tubería las personas del
     * género Mujer.
     *
     * Predicate es una interface funcional, es decir un tipo de interface que puede tener metodos por defecto, pero
     * SOLAMENTE UN METODO ABSTRACTO. Un predicate es una condicion que tiene que cumplir un elemento que circula por la
     * pipeline(tubería)
     *
     *
     * Clase Anonima
     * Es una clase que no tiene nombre, por lo cual no se puede utilizar para instancia un objeto, es decir es una expresion
     * de clase.
     * Se puede utilizar para instanciar un objeto a partir de una interface o una clase abstracta.
     */
}
