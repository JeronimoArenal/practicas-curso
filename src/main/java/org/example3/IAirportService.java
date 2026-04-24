package org.example3;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


//Define "qué" puede hacer el aeropuerto sin decir "cómo".
public interface IAirportService {
    void addFlight(Flight flight) throws AirportException; // Ahora avisa que puede fallar
    void registerPassenger(String flightCode, Passenger passenger);

    // Búsquedas y Procesamiento
    Optional<Flight> getNextFlightToDepart();               // en caso de null devuelve una caja. Usará una PriorityQueue internamente
    Map<String, Set<Passenger>> getFullManifest();          // Mapa de vuelos y sus pasajeros
    Collection<Flight> getFlightsByOrigin(String origin);   // Polimorfismo, ya qu devuelve un ArrayList que filtra


    // Gestión de Infraestructura
    void assignGate(int gateNumber, String flightCode);
    void releaseGate(int gateNumber);
    Map<Integer, Flight> getGateStatus(); // Ver qué hay en cada puerta


    // Busqueda Poimorfica de pasajeros en TODO el aeropuerto por coincidencia de nombre
    Collection<Passenger> searchPassengersByName(String nameQuery);
}