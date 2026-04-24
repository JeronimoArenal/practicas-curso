package org.example3;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        IAirportService airport = new AirportServiceImpl("Madrid");

        // --- 1. PREPARACIÓN DE DATOS (AQUÍ PODRÍAS TENER 50 VUELOS) ---
        List<Flight> vuelosNuevos = List.of(
                Flight.builder().code("IBE123").origin("Madrid").destination("Tokio")
                        .priority(FlightPriority.NORMAL).departureTime(LocalTime.of(22, 30)).build(),

                Flight.builder().code("IBE456").origin("Madrid").destination("Londres")
                        .priority(FlightPriority.NORMAL).departureTime(LocalTime.of(22, 30)).build(), // Fallará por hora

                Flight.builder().code("EMG-999").origin("Barcelona").destination("Ibiza")
                        .priority(FlightPriority.EMERGENCY).departureTime(LocalTime.of(22, 30)).build() // Fallará por origen
        );

        // Mapa para asociar pasajeros a cada código de vuelo
        Map<String, List<Passenger>> datosPasajeros = Map.of(
                "IBE123", List.of(
                        new Passenger("Carlos Pérez", "ABC001"),
                        new Passenger("Carlos P.", "ABC587")
                ),
                "IBE456", List.of(
                        new Passenger("Carlos Pacheco", "ABC587666"),
                        new Passenger("Rocío Martínez", "50836395R"),
                        new Passenger("Esther Arenal", "50836395E"),
                        new Passenger("Jerónimo Arenal", "50836395J")
                )
        );

        // --- 2. MOTOR DE PROCESAMIENTO MASIVO ---
        System.out.println("=== 1. PROCESAMIENTO DE VUELOS Y PASAJEROS ===");

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

        // --- 3. REPORTES Y BÚSQUEDAS ---
        System.out.println("\n=== 2. ESTADO DEL SISTEMA Y BÚSQUEDAS ===");
        System.out.println("Vuelos operativos en " + airport.getLocalOrigin() + ":");
        airport.getLocalFlights().forEach(f -> System.out.println(" - " + f.getCode() + " destino " + f.getDestination()));

        System.out.println("\nResumen de Manifiestos:");
        airport.getFullManifest().forEach((vuelo, pasajeros) ->
                System.out.println("Vuelo " + vuelo + ": " + pasajeros.size() + " pasajeros registrados.")
        );

        System.out.println("\nBúsqueda de 'Carlos' (Detallada):");

        // 1. Primero verificamos de forma global si hay alguien con ese nombre
        var resultados = airport.searchPassengersByName("Carlos");

        if (resultados.isEmpty()) {
            // Si la lista general está vacía, mostramos el aviso
            System.out.println("No se han localizado pasajeros con ese nombre en el sistema.");
        } else {
            // 2. Si hay resultados, usamos el mapa para mostrar en qué vuelo está cada uno
            airport.getFullManifest().forEach((codigoVuelo, pasajeros) -> {
                pasajeros.stream()
                        .filter(p -> p.getName().toLowerCase().contains("carlos"))
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
        airport.getGateStatus().forEach((num, f) -> System.out.println(" Puerta " + num + " -> " + f.getCode()));

        // --- 5. TORRE DE CONTROL: DESPEGUES (FLUJO DINÁMICO) ---
        System.out.println("\n=== 4. TORRE DE CONTROL: DESPEGUES ===");
        int contador = 1;
        Optional<Flight> proximoVuelo;

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
