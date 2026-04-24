package org.example3;

import java.util.*;
import java.util.stream.Collectors;

public class AirportServiceImpl implements IAirportService {

    // 1. Almacén de vuelos: Clave = Código de vuelo, Valor = Objeto Vuelo
    // Usamos Map para encontrar un vuelo instantáneamente sin recorrer una lista.
    private final Map<String, Flight> flightStorage = new HashMap<>();

    // TreeMap ordena las llaves (números de puerta) de forma natural
    private final Map<Integer, Flight> gates = new TreeMap<>();

    // 2. Diccionario de Pasajeros: Por cada código de vuelo, un Set de pasajeros quet garantiza que no haya duplicados
    // gracias al @EqualsAndHashCode de Passenger.
    private final Map<String, Set<Passenger>> passengerManifests = new HashMap<>();

    // 3. Cola de despegue: PriorityQueue usa el compareTo definido en Flight
    private final Queue<Flight> departureQueue = new PriorityQueue<>();


    @Override
    public void addFlight(Flight flight) throws AirportException {
        // 1. Validamos si la hora de salida es anterior a la hora actual
        if (flight.getDepartureTime().isBefore(java.time.LocalTime.now())) {
            throw new AirportException("ERROR: No se puede registrar el vuelo " + flight.getCode() +
                    ". La hora " + flight.getDepartureTime() + " ya ha pasado.");
        }

        // 2. Si la hora es válida, procedemos con el registro normal
        flightStorage.put(flight.getCode(), flight);
        passengerManifests.put(flight.getCode(), new HashSet<>());
        departureQueue.add(flight); // Se posiciona según su prioridad (Enum)
        System.out.println("Vuelo " + flight.getCode() + " registrado para las " + flight.getDepartureTime());
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
    public Optional <Flight> getNextFlightToDepart() {
        Flight flight = departureQueue.poll(); // Saca el vuelo con más prioridad

        if (flight != null) {
            // --- AQUÍ USAMOS EL POLIMORFISMO Y LOS MAPAS ---
            // Buscamos en qué puerta estaba este vuelo para liberarla
            Integer puertaAOcupada = null;

            for (Map.Entry<Integer, Flight> entry : gates.entrySet()) {
                if (entry.getValue().getCode().equals(flight.getCode())) {
                    puertaAOcupada = entry.getKey();
                    break;
                }
            }

            if (puertaAOcupada != null) {
                releaseGate(puertaAOcupada); // Llamamos al método que antes no usábamos
            }
        }

        // Usar ofNullable para que si es null, devuelva una caja vacía segura
        return Optional.ofNullable(flight);
    }

    @Override
    public void assignGate(int gateNumber, String flightCode) {
        Flight f = flightStorage.get(flightCode);
        if (f != null) {
            gates.put(gateNumber, f);
            System.out.println("Vuelo " + flightCode + " asignado a Puerta " + gateNumber);
        } else {
            System.out.println("Error: Vuelo no encontrado.");
        }
    }

    @Override
    public void releaseGate(int gateNumber) {
        Flight f = gates.remove(gateNumber); // El Map elimina la entrada y nos devuelve el objeto
        if (f != null) {
            System.out.println("LOG: La puerta " + gateNumber + " ha quedado libre.");
        }
    }

    @Override
    public Collection<Passenger> searchPassengersByName(String nameQuery) {
        return passengerManifests.values() // 1. Obtenemos todos los Set<Passenger> (Collection de Sets)
                .stream()                  // 2. Abrimos el flujo
                .flatMap(Set::stream)      // 3. ¡Clave! "Aplana" los Sets en un solo chorro de pasajeros
                .filter(p -> p.getName().toLowerCase().contains(nameQuery.toLowerCase())) // 4. Filtramos
                .distinct()                // 5. Por si un pasajero está en dos vuelos (gracias al EqualsAndHashCode)
                .collect(Collectors.toList()); // 6. Lo metemos en una lista (polimorfismo de salida)
    }

    @Override
    public Map<Integer, Flight> getGateStatus() {
        // Devolvemos el Map para que el cliente vea el estado
        return gates;
    }

    @Override
    public Map<String, Set<Passenger>> getFullManifest() {
        return Collections.unmodifiableMap(passengerManifests);
    }

    @Override
    public Collection<Flight> getFlightsByOrigin(String origin) {
        // Ejemplo de uso de Streams para filtrar colecciones
        return flightStorage.values().stream()
                .filter(f -> f.getOrigin().trim().equalsIgnoreCase(origin.trim()))  //.trim() elimina espacios invisibles antes o después del texto
                .collect(Collectors.toList());
        //.collect(Collectors.toSet()); // Ahora devuelve un HashSet en vez de ArrayList
    }
}