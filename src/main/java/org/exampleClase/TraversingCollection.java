package org.exampleClase;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Traversing Collections
 * https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html
 * Ver el documento ArraysenericsandCollection.pdf
 *
 * Existen 3 formas de recorrer una colección. de las cuales, solamnete 1 permite eliminar
 * elementos de una colección a la vez que se recorre.
 * 1º.- Utilizando la interface Iterator<E> que es la única forma de eliminar un elemento
 * mientras se recorre
 * 2º.- Utilizando una sentencia for mejorada
 * 3º.- Utilizando operaciones de agregado es decir: Programación funcional, métodos de la
 * clase Stream, métodos por referencia y operaciones lambda.
 */

public class TraversingCollection {


    //............................. iteratorOneCondition ........................

    public static void iteratorOneCondition(List<Persona> personas) {
        System.out.println("\n===== Listado Original ========");
        personas.forEach(System.out::println);

        //Recorremos la coleccion y eliminamos HOMBRE

        // 1º Forma: Usando Iterator para eliminar (Seguro)
        Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
            var p = it.next();	//Almaceno a la persona en p y puedes hacerle preguntas
            if (p.genero().equals(Genero.MASCULINO)) {
                it.remove(); // Eliminamos a los hombres
            }
        }

        // 2. Imprimimos el resultado FINAL
        System.out.println("===== Listado tras eliminar TODAS las persona de genero MASCULINO =====");
        if (personas.isEmpty()) {
            System.out.println("No queda nadie en la lista.");
        } else {
            // Usamos referencia a método para un listado limpio
            personas.forEach(System.out::println);
        }

        //Para evaluar una única condición se puede hacer sin declarar variable
/*      Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
        	if(it.next().genero().equals(Genero.MASCULINO)) {
                it.remove(); // Eliminamos a los hombres
            }
        }
*/
    }

    //............................. iteratorSeveralConditions ........................
    public static void iteratorSeveralConditions(List<Persona> personas) {
        //Utilizando un iterador eliminar del listado de personas los hombres y el nombre tenga 6 caracteres
        Iterator<Persona> it = personas.iterator();
        while (it.hasNext()) {
            Persona p = it.next();          //Nos permite hacerle las preguntas que queramos
            // 2. Verificamos:
            if (p.genero() != null && Genero.MASCULINO.equals(p.genero())
                    && p.nombre().trim().length() == 6) {
                it.remove();
            }
        }

        System.out.println("\n===== Listado tras eliminar MASCULINO con nombre de 6 caracteres(Iterator) ========");
        personas.forEach(System.out::println);
    }

    //............................. eliminarMujeresMethod ........................
    //Iterar para recorrer utilizando for mejorado
    public static void eliminarMujeres(List<Persona> personas) {
        List<Persona> aEliminar = new ArrayList<>();

        // Usamos el for mejorado para identificar quiénes se van
        for (var p : personas) {
            //Forma optima:
            //1º Si Genero es null, lanza un error.
            //2º Como Genero es una constante existe y su método .equals() siempre se puede ejecutar,
            //aunque pases un null porque te dira que no son iguales (false)
            if (Genero.FEMENINO.equals(p.genero())) {
                //personas.remove(p); // ¡ERROR AQUÍ!
                aEliminar.add(p);
            }
        }
        // Borramos todos los encontrados
        personas.removeAll(aEliminar);
        System.out.println("\n=== Personas del género FEMENINO eliminadas ===");

        // Usando referencia a método para imprimir cada una en una línea
        aEliminar.forEach(System.out::println);

    }
}
