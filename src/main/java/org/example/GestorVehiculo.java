package org.example;

import java.util.List;

public class GestorVehiculo {


    //............................... Methods .........................................
    /* Métodos static (de clase) para no tener que crear necesariamente un objeto para llamarlos
    * REGLA: dentro de un método static sólo se puede llamar a métodos static.
    *                        SI podrás llamar a un método de instancia si primero creas un objeto en el mismo método*/

    //Se encarga del flujo y recorre la lista para asegurarse que no está vacia
    public static void procesarFlota(List<Vehiculo> flota) {
        if (flota == null || flota.isEmpty()) {
            System.out.println("La flota está vacía. Nada que procesar.");
            return;
        }
        System.out.println("PROCESANDO FLOTA DE " + flota.size() + " VEHÍCULOS...");
        //Se puede hacer de 3 maneras:
        //flota.forEach(v -> presentarVehiculo(v));             //Expresion Lambda. Puedes pasar variables y argumentos
        //flota.forEach(GestorVehiculo::presentarVehiculo);     //Referencia a método. SOLO con 1 argumento
        for (Vehiculo v : flota) {                              // Mejor cuando la logica tiene 2 o mas condiciones. Gestiona try-catch de forma limpia
            presentarVehiculo(v);                               // Puedes controlar el fujo (break y continue). Saber el índice por el que vas
        }                                                       //Puedes sumar, restar y cambiar variables que esten fuera del bucle. El más rapido.
    }

    // Con static acepta cualquier clase que herede de Vehículo, se encarga de que hacer con cada vehiculo segun su tipo
    //Aquí creamos un objeto y por eso podemos utilizar metodos de instancia
    public static void presentarVehiculo(Vehiculo v) {
        System.out.println("=== REPORTE DE VEHÍCULO ===");

        // 1. Polimorfismo en los datos: Cada clase sabe cómo imprimirse ya que java sabe que tipo de vehiculo es "v"
        // llamando a su correspondiente toString
        System.out.println(v.toString());

        // 2. Polimorfismo en la acción: CERO 'if', CERO 'instanceof', CERO 'casting'
        // Cada objeto (Helicóptero, Bici, etc.) ejecuta su propia lógica interna
        v.realizarAccionEspecial();

        System.out.println("===========================\n");
    }
}
