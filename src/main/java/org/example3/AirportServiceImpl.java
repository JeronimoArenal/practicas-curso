package org.example3;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AirportServiceImpl implements IAirportService {
    private final String localOrigin;

    // Constructor para asignar el nombre al crear el servicio
    public AirportServiceImpl(String localOrigin) {
        this.localOrigin = localOrigin;
    }


    // 1. Almacén de vuelos: Clave = Código de vuelo, Valor = Objeto Vuelo
    // Usamos Map para encontrar un vuelo instantáneamente sin recorrer una lista.
    private final Map<String, Flight> flightStorage = new HashMap<>();

    // TreeMap ordena las llaves (números de puerta) de forma natural
    private final Map<Integer, Flight> gates = new TreeMap<>();

    // 2. Diccionario de Pasajeros: Por cada código de vuelo, un Set de pasajeros que garantiza que no haya duplicados
    // gracias al @EqualsAndHashCode de Passenger.
    private final Map<String, Set<Passenger>> passengerManifests = new HashMap<>();

    // 3. Cola de despegue: PriorityQueue usa el compareTo definido en Flight
    private final Queue<Flight> departureQueue = new PriorityQueue<>();


    @Override
    public void addFlight(Flight flight) throws AirportException {
        // NUEVA REGLA: Si no sale de aquí, no se registra
        if (!flight.getOrigin().equalsIgnoreCase(this.localOrigin)) {
            throw new AirportException("Denegado: El vuelo " + flight.getCode() +
                    " sale de " + flight.getOrigin() + " y no puede operar en " + this.localOrigin);
        }

        if (flight.getDepartureTime().isBefore(LocalTime.now())) {
            throw new AirportException("ERROR: La hora ya ha pasado.");
        }

        flightStorage.put(flight.getCode(), flight);
        passengerManifests.put(flight.getCode(), new HashSet<>());
        departureQueue.add(flight);
        System.out.println("Vuelo " + flight.getCode() + " registrado con éxito.");
    }

    @Override
    public String getLocalOrigin() {
        return this.localOrigin;
    }

/*    @Override
    public Collection<Flight> getFlightsByOrigin(String origin) {
        // Ejemplo de uso de Streams para filtrar colecciones
        return flightStorage.values().stream()
                .filter(f -> f.getOrigin().trim().equalsIgnoreCase(origin.trim()))  //.trim() elimina espacios invisibles antes o después del texto
                .toList();      //Sustituimos esto .collect(Collectors.toList());
        //.collect(Collectors.toSet()); // Ahora devuelve un HashSet en vez de ArrayList
    }
*/
    @Override
    public Collection<Flight> getLocalFlights() {
        // Usamos el origen que guardamos en el constructor (localOrigin) para filtrar automáticamente todos los vuelos
        return flightStorage.values().stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(this.localOrigin))
                .toList();      //Sustituimos esto .collect(Collectors.toList());
    }

    @Override
    public void registerPassenger(String flightCode, Passenger passenger) {
        if (!flightStorage.containsKey(flightCode)) {
            System.out.println("Error: El vuelo " + flightCode + " no existe.");
            return;
        }
        // Obtenemos el Set de ese vuelo y añadimos al pasajero evitando duplicados
        passengerManifests.get(flightCode).add(passenger);
    }

    @Override
    public Map<String, Set<Passenger>> getFullManifest() {
        return Collections.unmodifiableMap(passengerManifests);
    }

    @Override
    public Collection<Passenger> searchPassengersByName(String nameQuery) {
        return passengerManifests.values() // 1. Obtenemos todos los Set<Passenger> (Collection de Sets)
                .stream()                  // 2. Abrimos el flujo
                .flatMap(Set::stream)      // 3. ¡Clave! "Aplana" los Sets en un solo chorro de pasajeros
                .filter(p -> p.getName().toLowerCase().contains(nameQuery.toLowerCase())) // 4. Filtramos
                .distinct()                // 5. Por si un pasajero está en dos vuelos (gracias al EqualsAndHashCode)
                .toList(); // 6. Lo metemos en una lista (polimorfismo de salida)
    }

    // Esta es la mejor opción en Java moderno con removeIf
    @Override
    public Optional<Flight> getNextFlightToDepart() {
        Flight flight = departureQueue.poll();

        if (flight != null) {
            // 1. Primero anunciamos que el vuelo se mueve
            System.out.println("Saliendo: " + flight.getCode());

            // 2. Después buscamos y liberamos la puerta usando tu método releaseGate
            gates.entrySet().stream()
                    .filter(entry -> entry.getValue().getCode().equals(flight.getCode()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .ifPresent(this::releaseGate); // Esto imprimirá "LOG: La puerta X ha quedado libre"
        }

        return Optional.ofNullable(flight);
    }

    @Override
    public void assignGate(int gateNumber, String flightCode) {
        Flight f = flightStorage.get(flightCode);

        if (f == null) {
            System.out.println("Error: Vuelo no encontrado.");
            return;
        }

        // VALIDACIÓN DE COHERENCIA
        if (!f.getOrigin().equalsIgnoreCase(localOrigin)) {
            System.out.println("ERROR: No se puede asignar puerta en " + localOrigin +
                               " al vuelo " + flightCode + " que sale de " + f.getOrigin());
            return;
        }

        gates.put(gateNumber, f);
        System.out.println("Vuelo " + flightCode + " asignado a Puerta " + gateNumber);
    }

    @Override
    public void releaseGate(int gateNumber) {
        Flight f = gates.remove(gateNumber);        // El Map elimina la entrada y nos devuelve el objeto
        if (f != null) {
            System.out.println("LOG: La puerta " + gateNumber + " ha quedado libre.");
        }
    }

    @Override
    public Map<Integer, Flight> getGateStatus() {
        // Devolvemos el Map para que el cliente vea el estado
        return gates;
    }

}