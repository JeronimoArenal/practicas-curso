package org.example4;

import java.util.List;
/*
Abstracción: Una clase Base para no repetir campos.
Genéricos: Un BaseService que sirve para cualquier cosa.
Interfaces: Para definir comportamientos como realizarAccionEspecial.
Desacoplamiento: Los DTOs para que el exterior no vea tus entidades internas.
Robustez: Manejo de excepciones para que el programa no "explote".
 */

public class Main {

    public static void main(String[] args) {

        // 1. Instanciamos los servicios (Motor de Gestión)
        ICocheService cocheService = new CocheServiceImpl();
        ICruceroService cruceroService = new CruceroServiceImpl();

        // 2. Instanciamos el Mapper (Motor de Transformación)
        // Al ser Java puro, lo creamos manualmente para usarlo después
        VehiculoConverter vehiculoMapper = new VehiculoConverter();

        System.out.println("--- INICIALIZANDO REGISTRO DE VEHÍCULOS ---\n");

        try {
            // 3. REGISTRO (Lógica de BaseService + SuperBuilder)
            Coche rayo = Coche.builder()
                    .id(101L)
                    .name("Rayo McQueen")
                    .marca("Rust-eze")
                    .modelo("V8")
                    .numeroRuedas(4)
                    .build();
            cocheService.save(rayo);

            Crucero titanic = Crucero.builder()
                    .id(505L)
                    .name("Titanic II")
                    .marca("White Star Line")
                    .modelo("Olympic Class")
                    .capacidadPasajeros(2500)
                    .build();
            cruceroService.save(titanic);

            System.out.println("\n--- PRUEBA DE BÚSQUEDA Y ACCIONES ---");

            // 4. ACCIÓN (Polimorfismo: el objeto sabe qué hacer)
            Coche recuperado = cocheService.findByUrl("rayo-mcqueen");
            if (recuperado != null) {
                System.out.print("Acción de " + recuperado.getName() + ": ");
                recuperado.realizarAccionEspecial();
            }

            System.out.println("\n--- VISTA DE USUARIO (USANDO DTOs) ---");

            // 5. TRANSFORMACIÓN (Aquí usamos tu fromEntityList con .toList())
            // Recuperamos todas las entidades pesadas
            List<Coche> todosLosCoches = cocheService.findAll();
            List<Crucero> todosLosCruceros = cruceroService.findAll();

            // Las convertimos a DTOs ligeros para "mostrarlos en la web"
            // Gracias a los genéricos, el mismo mapper puede procesar ambas listas
            List<VehiculoDTO> catalogoCoches = vehiculoMapper.fromEntityList(todosLosCoches);
            List<VehiculoDTO> catalogoCruceros = vehiculoMapper.fromEntityList(todosLosCruceros);

            // Imprimimos los DTOs usando la sintaxis de Record
            catalogoCoches.forEach(dto -> System.out.println("DTO Recibido -> " + dto.nombreCompleto() + " [" + dto.tipoVehiculo() + "]"));
            catalogoCruceros.forEach(dto -> System.out.println("DTO Recibido -> " + dto.nombreCompleto() + " [" + dto.tipoVehiculo() + "]"));


            // 6. PRUEBA DE ERRORES
            System.out.println("\n--- PROBANDO VALIDACIONES ---");
            Coche cocheInvalido = Coche.builder().id(99L).name("").build();
            cocheService.save(cocheInvalido);

        } catch (DomainException e) {
            System.err.println("ERROR DE NEGOCIO: " + e.getMessage());
        } catch (ResourceNotFoundException e) {
            System.err.println("ERROR DE BÚSQUEDA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR INESPERADO: " + e.getMessage());
        }
    }

    // Método auxiliar para mostrar cualquier tipo de vehículo
    public static void mostrarResumen(List<? extends Vehiculo> flota) {
        for (Vehiculo v : flota) {
            System.out.println("ID: " + v.getId() + " | " + v.getName() + " [" + v.getUrl() + "]");
        }
    }
}
