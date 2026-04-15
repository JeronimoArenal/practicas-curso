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
        System.out.println("🚀 PROCESANDO FLOTA DE " + flota.size() + " VEHÍCULOS...");
        for (Vehiculo v : flota) {
            presentarVehiculo(v);
        }
    }

    // Con static acepta cualquier clase que herede de Vehículo, se encarga de que hacer con cada vehiculo segun su tipo
    //Aquí creamos un objeto y por eso podemos utilizar metodos de instancia
    public static void presentarVehiculo(Vehiculo v) {
        System.out.println("=== REPORTE DE VEHÍCULO ===");

        // 1. Polimorfismo en los datos: Cada clase sabe cómo imprimirse
        System.out.println(v.toString());

        // 2. Polimorfismo en la acción: CERO 'if', CERO 'instanceof', CERO 'casting'
        // Cada objeto (Helicóptero, Bici, etc.) ejecuta su propia lógica interna
        v.realizarAccionEspecial();

        System.out.println("===========================\n");
    }
}
