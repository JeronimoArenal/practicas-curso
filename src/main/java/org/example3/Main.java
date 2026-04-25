package org.example3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        IAirportService airport = new AirportServiceImpl("Madrid");

        // --- 1. PREPARACIÓN DE DATOS  ---
        //List<Flight> vuelosNuevos = new ArrayList<>(List.of(      // Lista mutable
        List<Flight> vuelosNuevos = List.of(        //List.of() Factory Method crea una lista inmutable
                Flight.builder().code("IBE123").origin("Madrid").destination("Tokio")
                        .priority(FlightPriority.NORMAL).departureTime(LocalTime.of(22, 30)).build(),

                Flight.builder().code("IBE456").origin("Madrid").destination("Londres")
                        .priority(FlightPriority.NORMAL).departureTime(LocalTime.of(23, 30)).build(), // Fallará por hora

                Flight.builder().code("EMG-999").origin("Barcelona").destination("Ibiza")
                        .priority(FlightPriority.EMERGENCY).departureTime(LocalTime.of(22, 30)).build() // Fallará por origen
        );

        // Se puede usar el método .add() fuera de la declaración inicial siempre que se utilice ArrayList<>
    /*    vuelosNuevos.add(
                Flight.builder()
                        .code("IBE789")
                        .origin("Madrid")
                        .destination("París")
                        .priority(FlightPriority.NORMAL)
                        .departureTime(LocalTime.of(10, 15))
                        .build()
        );
    */
        // Mapa para asociar pasajeros a cada código de vuelo
        Map<String, List<Passenger>> datosPasajeros = Map.of(
                "IBE123", List.of(
                        new Passenger("Miguel Arenal", "ABC001"),
                        new Passenger("Miguel Martínez", "ABC587")
                ),
                "IBE456", List.of(
                        new Passenger("Miguel Martínez", "ABC587666"),
                        new Passenger("Rocío Martínez", "50836395R"),
                        new Passenger("Esther Arenal", "50836395E"),
                        new Passenger("Jerónimo Arenal", "50836395J")
                )
        );

        // --- 2. MOTOR DE PROCESAMIENTO MASIVO ---
        System.out.println("=== 1. PROCESAMIENTO DE VUELOS ===");

        vuelosNuevos.forEach(vuelo -> {
            try {
                // Paso A: Intentar registrar el vuelo
                airport.addFlight(vuelo);

                // Paso B: Si el vuelo se registró, buscamos si tiene pasajeros asociados y los metemos
                if (datosPasajeros.containsKey(vuelo.getCode())) {
                    datosPasajeros.get(vuelo.getCode()).forEach(p ->
                            airport.registerPassenger(vuelo.getCode(), p)
                    );
                }
            } catch (AirportException e) {
                // Si falla el vuelo, este bloque captura el error y salta al siguiente vuelo automáticamente
                System.out.println("LOG: Vuelo " + vuelo.getCode() + " descartado -> " + e.getMessage());
            }
        });

        System.out.println("\n--- PRÓXIMOS DESTINOS (Stream)---");
        airport.getLocalFlights().stream()
                .map(f -> f.getDestination().toUpperCase())    // Transformamos el destino a MAYÚSCULAS
                .distinct()                                         // Si hay dos vuelos a Tokio, que solo salga una vez
                .sorted()                                           // Ordenamos de la A a la Z
                .forEach(destino -> System.out.println(destino));

        // --- 3. REPORTES Y BÚSQUEDAS ---
        //System.out.println("\n=== 2. ESTADO DEL SISTEMA Y BÚSQUEDAS ===");
        System.out.println("Vuelos operativos en " + airport.getLocalOrigin() + ":");
        airport.getLocalFlights().forEach(f -> System.out.println(" - " + f.getCode() + " destino " + f.getDestination()));


        System.out.println("\n=== 2. PROCESAMIENTO DE PASAJEROS ===");
        airport.getFullManifest().forEach((vuelo, pasajeros) ->
                System.out.println("Vuelo " + vuelo + ": " + pasajeros.size() + " pasajeros registrados.")
        );

        // 1. Obtenemos todos los manifiestos (Map<String, Set<Passenger>>)
        // 2. Sacamos solo los valores (Collection<Set<Passenger>>)
        // 3. ¡Usamos flatMap para unir todos los Sets en un solo Stream!
        List<Passenger> todosLosPasajeros = airport.getFullManifest().values().stream()
                .flatMap(set -> set.stream())   // Entra un Set de pasajeros, salen los pasajeros sueltos
                .distinct()                                     // Por si un pasajero está en dos vuelos (escala), que no se repita
            //  .collect(Collectors.toList());      //lista SI modificable
                .toList();                          //lista NO modificable


        System.out.println("Total de personas en la terminal: " + todosLosPasajeros.size());
        todosLosPasajeros.forEach(p -> System.out.println("- " + p.getName()));

// --- 3. REPORTES Y BÚSQUEDAS ---
        String nombreABuscar = "Miguel"; // <--- Centralizamos aquí el valor
        String queryMinusculas = nombreABuscar.toLowerCase();

        System.out.println("\nBúsqueda de '" + nombreABuscar + "' (Detallada):");

// 1. Verificación global usando la variable
        var resultados = airport.searchPassengersByName(nombreABuscar);

        if (resultados.isEmpty()) {
            System.out.println("No se han localizado pasajeros con el nombre '" + nombreABuscar + "' en el sistema.");
        } else {
            // 2. Si hay resultados, usamos el mapa para mostrar la ubicación
            airport.getFullManifest().forEach((codigoVuelo, pasajeros) -> {
                pasajeros.stream()
                        .filter(p -> p.getName().toLowerCase().contains(queryMinusculas)) // <--- Variable reutilizada
                        .forEach(p -> System.out.println(" [" + codigoVuelo + "] Pasajero: " + p.getName()
                                + " [ID: " + p.getPassport() + "]"));
            });
        }
        // --- 4. LOGÍSTICA DE TIERRA (PUERTAS) ---
        System.out.println("\n=== 3. ASIGNACIÓN DE PUERTAS AUTOMÁTICA ===");
        int numeroPuerta = 1;
        for (Flight f : airport.getLocalFlights()) {
            airport.assignGate(numeroPuerta++, f.getCode());
        }

        System.out.println("Estado de puertas actual:");
        airport.getGateStatus().forEach((num, f) ->
                System.out.println(" Puerta " + num + " -> " + f.getCode()));

        // --- 5. TORRE DE CONTROL: DESPEGUES (FLUJO DINÁMICO) ---
        System.out.println("\n=== 4. TORRE DE CONTROL: DESPEGUES ===");
        int contador = 1;
        Optional<Flight> proximoVuelo;
        //Comprobacion vuelos nocturnos (stream)
        boolean hayVuelosNocturnos = airport.getLocalFlights().stream()
                .anyMatch(f -> f.getDepartureTime().isAfter(LocalTime.of(23, 0)));

        if (hayVuelosNocturnos) {
            System.out.println("Aviso: Preparar personal para turno de noche.");
        }

        while ((proximoVuelo = airport.getNextFlightToDepart()).isPresent()) {
            Flight f = proximoVuelo.get();
            System.out.println("Despegue #" + contador + ": Confirmado " + f.getCode() + " [" + f.getPriority() + "]");
            contador++;
        }

        if (contador == 1) {
            System.out.println("No había vuelos programados para despegar.");
        } else {
            System.out.println("Torre de control: Pista libre. Todos los vuelos han salido.");
        }


        // --- 6. VERIFICACIÓN FINAL ---
        System.out.println("\n=== 5. ESTADO FINAL DEL AEROPUERTO ===");

        if (airport.getGateStatus().isEmpty()) {
            System.out.println("Todas las puertas están libres. Operación finalizada con éxito.");
        } else {
            airport.getGateStatus().forEach((num, f) ->
                    System.out.println(" Puerta " + num + " sigue ocupada por: " + f.getCode()));
        }
    }

}
