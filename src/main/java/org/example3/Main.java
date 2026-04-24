package org.example3;

import java.time.LocalTime;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        IAirportService airport = new AirportServiceImpl();

        // --- 1. PROGRAMACIÓN DE VUELOS CON TRY-CATCH ---
        System.out.println("=== 1. PROGRAMACIÓN DE VUELOS ===");
        try {
            // Ajustamos horas para que sean válidas (futuras) según el momento del sistema
            Flight v1 = new Flight("IBE123", "Madrid", "Tokio", FlightPriority.NORMAL, LocalTime.of(22, 30));
            Flight v2 = new Flight("VLG456", "Madrid", "París", FlightPriority.NORMAL, LocalTime.of(23, 30));
            Flight v3 = new Flight("EMG-999", "Barcelona", "Ibiza", FlightPriority.EMERGENCY, LocalTime.of(23, 30));

            airport.addFlight(v1);
            airport.addFlight(v2);
            airport.addFlight(v3);
            System.out.println("Vuelos registrados con éxito."

            );

        } catch (AirportException e) {
            System.err.println("Error en registro: " + e.getMessage());
        }

        // Prueba de búsqueda de vuelos
        System.out.println("=== BÚSQUEDA DE VUELOS ===");
        airport.getFlightsByOrigin("madrid").forEach(f ->
                System.out.println("Vuelo encontrado: " + f.getCode() + " con destino " + f.getDestination()));


        System.out.println("Total vuelos en sistema: " + airport.getFullManifest().keySet().size());
        airport.getFullManifest().keySet().forEach(code -> System.out.println(" - " + code));


        // --- 2. GESTIÓN DE PASAJEROS (CON VALIDACIÓN DEFENSIVA) ---
        System.out.println("\n=== 2. REGISTRO DE PASAJEROS ===");
        airport.registerPassenger("IBE123", new Passenger("Carlos Pérez", "ABC001"));
        airport.registerPassenger("IBE123", new Passenger("Carlos P.", "ABC587"));

        // Usamos Optional o validamos nulo para el manifest
        Optional.ofNullable(airport.getFullManifest().get("IBE123"))
                .ifPresentOrElse(
                        list -> System.out.println("Pasajeros únicos en IBE123: " + list.size()),
                        () -> System.out.println("No se encontraron pasajeros para IBE123 (Vuelo quizás no registrado)")
                );

        // Prueba de búsqueda de pasajeros
        System.out.println("\n=== BÚSQUEDA DE PERSONAS ===");
        airport.searchPassengersByName("Carlos").forEach(p ->
                System.out.println("Pasajero localizado: " + p.getName() + " [ID: " + p.getPassport() + "]"));

        // --- 3. LOGÍSTICA DE TIERRA (PUERTAS) ---
        System.out.println("\n=== 3. ASIGNACIÓN DE PUERTAS ===");
        airport.assignGate(10, "IBE123");
        airport.assignGate(5, "VLG456");
        airport.assignGate(1, "EMG-999");

        System.out.println("Estado de puertas actual:");
        airport.getGateStatus().forEach((num, f) -> System.out.println(" Puerta " + num + " -> " + f.getCode()));

        // --- 4. OPERACIONES DE VUELO (DESPEGUES CON OPTIONAL) ---
        System.out.println("\n=== 4. TORRE DE CONTROL: DESPEGUES ===");

        // Intentamos despegar 4 veces (aunque solo hay 3 vuelos) para probar la robustez
        for (int i = 1; i <= 4; i++) {
            System.out.print("Intento de despegue #" + i + ": ");
            airport.getNextFlightToDepart().ifPresentOrElse(
                    f -> System.out.println("Saliendo: " + f.getCode() + " [" + f.getPriority() + "]"),
                    () -> System.out.println("Pista vacía. No hay más aviones.")
            );
        }

        // --- 5. VERIFICACIÓN FINAL ---
        System.out.println("\n=== 5. ESTADO FINAL DEL AEROPUERTO ===");
        if (airport.getGateStatus().isEmpty()) {
            System.out.println("Todas las puertas están libres.");
        } else {
            airport.getGateStatus().forEach((num, f) ->
                    System.out.println(" Puerta " + num + " sigue ocupada por: " + f.getCode()));
        }
    }

}
