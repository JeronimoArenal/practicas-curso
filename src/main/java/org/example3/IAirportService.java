package org.example3;

import java.util.*;
import java.util.function.Predicate;

/**
 * EL CONTRATO
 * Define "qué" puede hacer el aeropuerto sin decir "cómo".
 */

public interface IAirportService {

    void addFlight(Flight flight) throws AirportException;   // Avisa que puede fallar
    void registerPassenger(String flightCode, Passenger passenger);

    // Búsquedas y Procesamiento
    Optional<Flight> getNextFlightToDepart();               // en caso de null devuelve una caja. Usará una PriorityQueue internamente
    Map<String, Set<Passenger>> getFullManifest();          // Mapa de vuelos y sus pasajeros
   // Collection<Flight> getFlightsByOrigin(String origin); // Polimorfismo, ya que devuelve un ArrayList que filtra
    String getLocalOrigin();                                // Para saber de dónde es este aeropuerto
    Collection<Flight> getLocalFlights();                   // Para buscar automáticamente


    // Gestión de Infraestructura
    void assignGate(int gateNumber, String flightCode);
    void releaseGate(int gateNumber);
    Map<Integer, Flight> getGateStatus(); // Ver qué hay en cada puerta

    // Busqueda Poimorfica de pasajeros en TODO el aeropuerto por coincidencia de nombre
    Collection<Passenger> searchPassengersByName(String nameQuery);

    //Predicate
    List<Flight> buscarVuelos(Predicate<Flight> filtro);
}