package org.example4;

import java.util.List;

public class Main {

    public static void main(String[] args){

    // 1. Instanciamos los servicios (El Motor Genérico)
    ICocheService cocheService = new CocheServiceImpl();
    ICruceroService cruceroService = new CruceroServiceImpl();

        System.out.println("--- 🛡️ INICIALIZANDO REGISTRO DE VEHÍCULOS ---\n");

        try {
        // 2. Creamos y guardamos un Vehículo Terrestre (Coche)
        Coche rayo = Coche.builder()
                .id(101L)
                .name("Rayo McQueen")
                .marca("Rust-eze")
                .numeroRuedas(4)
                .build();

        cocheService.save(rayo);

        // 3. Creamos y guardamos un Vehículo Marítimo (Crucero)
        // Fíjate que NO le ponemos URL, el servicio la creará
        Crucero titanic = Crucero.builder()
                .id(505L)
                .name("Titanic II")
                .marca("White Star Line")
                .capacidadPasajeros(2500)
                .build();

        cruceroService.save(titanic);

        System.out.println("\n--- 🔍 PRUEBA DE BÚSQUEDA Y ACCIONES ---");

        // 4. Buscamos por URL (Lógica del BaseService)
        Coche recuperado = cocheService.findByUrl("rayo-mcqueen");
        if (recuperado != null) {
            System.out.println("Vehículo encontrado: " + recuperado.getName());
            // Ejecutamos su acción especial (Lógica de la Jerarquía)
            recuperado.realizarAccionEspecial();
        }

        // 5. Probamos el polimorfismo con el Crucero
        Crucero cruceroRecuperado = cruceroService.findById(505L);
        System.out.println("URL generada para el crucero: " + cruceroRecuperado.getUrl());
        cruceroRecuperado.realizarAccionEspecial();

        // 6. PRUEBA DE ERRORES (Para ver que las excepciones funcionan)
        System.out.println("\n--- PROBANDO VALIDACIONES ---");

        // Intento de guardar sin nombre (Lanzará DomainException)
        Coche cocheInvalido = Coche.builder().id(99L).name("").build();
        cocheService.save(cocheInvalido);

    } catch (DomainException e) {
        System.err.println("ERROR DE NEGOCIO: " + e.getMessage());
    } catch (ResourceNotFoundException e) {
        System.err.println("ERROR DE BÚSQUEDA: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("ERROR INESPERADO: " + e.getMessage());
    }

    // 7. MOSTRAR TODA LA FLOTA
    mostrarResumen(cocheService.findAll());
    mostrarResumen(cruceroService.findAll());
}

    // Método auxiliar para mostrar cualquier tipo de vehículo
    public static void mostrarResumen(List<? extends Vehiculo> flota) {
        for (Vehiculo v : flota) {
            System.out.println("ID: " + v.getId() + " | " + v.getName() + " [" + v.getUrl() + "]");
        }
    }
}
