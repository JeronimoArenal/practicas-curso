package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorVehiculoService {

    // La instancia única (Singleton - Variante Eager initialization). Mejor entre otras razones: Thread-Safety Nativo
    private static final GestorVehiculoService INSTANCIA = new GestorVehiculoService();
    private final List<Vehiculo> flota = new ArrayList<>();     // La flota ahora vive AQUÍ, no en el Main (Encapsulamiento)

    /**         LAZY INITIALIZATION
     *     private static GestorVehiculoService instancia = null;
     *
     *     //El mismo constructor
     *     private GestorVehiculoService() {}
     *
     *     // 3. El método get() tiene la lógica de "creación bajo demanda" asegurada multi-hilo
     *     public static GestorVehiculoService get() {
     *          // 1ª comprobación
     *         if (instancia == null) {
     *          synchronized (GestorVehiculosService.class){
     *              // 2ª comprobación
     *              if(instancia == null){
     *                  instancia = new GestorVehiculoService();
     *              }
     *          }
     *         }
     *         return instancia;
     *     }
     */



    //............................... Constructor privado .........................................
    private GestorVehiculoService() {}                               // Prohibimos que alguien haga 'new GestorVehiculo()'


    //............................... M E T H O D S  .........................................

    //......................... Encargado de ciclo de vida de la UNICA instancia (Singleton) ........................
    public static GestorVehiculoService get() {
        return INSTANCIA;
    }

    //............................... Registrar vehículos (Usamos Varargs)  ...............................
    public void registrar(Vehiculo... nuevos) {
        if (nuevos != null) {
            for (Vehiculo v : nuevos) {
                if (v != null) {
                    this.flota.add(v);
                }
            }
        }
    }

    //............................... Procesar la Flota  .....................................
    public void procesarFlota() {
        if (flota.isEmpty()) {
            System.out.println("La flota está vacía. Nada que procesar.");
            return;
        }

        System.out.println("PROCESANDO FLOTA DE " + flota.size() + " VEHÍCULOS...");
        for (int i = 0; i < flota.size(); i++) {
            System.out.println(">>> Procesando vehículo [" + (i + 1) + " de " + flota.size() + "]");
            presentarVehiculo(flota.get(i));
        }
    }

    //............................... Presentar la Flota  .....................................
    private void presentarVehiculo(Vehiculo v) {
        System.out.println("=== REPORTE DE VEHÍCULO ===");
        System.out.println(v.toString());
        v.realizarAccionEspecial();
        System.out.println("===========================\n");
    }

    //............................... Buscador  .....................................
    public void buscarPorNombre(String nombre) {
        boolean existe = flota.stream()
                .anyMatch(v -> v.getNombre().equalsIgnoreCase(nombre));

        //True, se ejecuta lo que va después de ?. False lo que hay despues de :
        System.out.println(existe ? "Encontrado: " + nombre : "No hay rastro de: " + nombre);
    }

}
