package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    //............................... Buscador (true/false) .....................................
    public void buscarPorNombre(String nombre) {
        boolean existe = flota.stream()
                .anyMatch(v -> v.getNombre().equalsIgnoreCase(nombre));

        //True, se ejecuta lo que va después de ?. False lo que hay despues de :
        System.out.println(existe ? "Encontrado: " + nombre : "No hay rastro de: " + nombre);
    }

    //.......................... Ordenar y comprar objetos con Comparator ......................
    //ORDENAR
    public void ordenarPorNombreYFabricante() {
        flota.sort(Comparator
                .comparing(Vehiculo::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Vehiculo :: getModelo)
                .thenComparing(
                        Comparator.comparing(Vehiculo::getFabricante).reversed())
        );
        System.out.println("Flota ordenada por nombre y fabricante.");
    }

    //COMPARAR NOMBRE
    public boolean tienenMismoNombre(Vehiculo v1, Vehiculo v2) {
        if (v1 == null || v2 == null) return false;
        return v1.getNombre().equalsIgnoreCase(v2.getNombre());
    }

    //BUSCAR CONCRETO
    public Optional<Vehiculo> obtenerPorNombre(String nombre) {
        return flota.stream()
                .filter(v -> v.getNombre().equalsIgnoreCase(nombre))
                .findFirst(); // Devuelve el primer vehículo que coincida
    }

    // En GestorVehiculoService
    public void realizarMantenimientoFiltrado(Predicate<Vehiculo> filtro, IMantenimiento tipoMantenimiento) {
        System.out.println("--- INICIANDO PROTOCOLO DE MANTENIMIENTO SELECCIONADO ---");

        flota.stream()
                .filter(filtro) // Aquí aplicamos la condición (ej: que tenga +2 motores)
                .forEach(v -> {
                    String resultado = tipoMantenimiento.ejecutar(v);
                    System.out.println(v.getNombre() + " (" + v.getFabricante() + "): " + resultado);
                });
    }

    public List<Vehiculo> obtenerVehiculosQueCumplen(Predicate<Vehiculo> criterio) {
        return flota.stream()
                .filter(criterio) // filter() usa internamente la interfaz Predicate
                .collect(Collectors.toList());
    }

}
